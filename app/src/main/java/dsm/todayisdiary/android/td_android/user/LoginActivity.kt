package dsm.todayisdiary.android.td_android.user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.util.Log
import android.widget.Toast
import dsm.todayisdiary.android.td_android.data.RetrofitBuilder
import dsm.todayisdiary.android.td_android.data.request.user.UserLoginRequest
import dsm.todayisdiary.android.td_android.data.response.user.UserLoginResponse
import dsm.todayisdiary.android.td_android.data.util.MyApplication
import dsm.todayisdiary.android.td_android.databinding.ActivityLoginBinding
import dsm.todayisdiary.android.td_android.diary.HomeActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retrofitBuilder = RetrofitBuilder

        binding.loginButtonLogin.setOnClickListener {
            Log.d(
                "id, pw",
                "\nid ${binding.accountIdLogin.text.toString()} \npw ${binding.passwordLogin.text.toString()}"
            )
            retrofitBuilder.userApi.userLogin(
                UserLoginRequest(
                    binding.accountIdLogin.text.toString(),
                    binding.passwordLogin.text.toString()
                )
            ).enqueue(object : Callback<UserLoginResponse> {
                override fun onFailure(call: Call<UserLoginResponse>, t: Throwable) {
                    Log.d("통신 실패", "${t.message}")
                }

                override fun onResponse(
                    call: Call<UserLoginResponse>,
                    response: Response<UserLoginResponse>
                ) {
                    if (response.isSuccessful) {
                        val accessToken = response.body()?.accessToken
                        val refreshToken = response.body()?.refreshToken
                        MyApplication.prefs.setString("atk", "$accessToken")
                        MyApplication.prefs.setString("rtk", "$refreshToken")
                        Log.d(
                            "Token",
                            "atk : ${MyApplication.prefs.getString("atk", "")
                            } \nrtk : ${MyApplication.prefs.getString("rtk", "")}"
                        )
                        startActivity(Intent(this@LoginActivity, HomeActivity::class.java));
                        finish()
                    } else {
                        Log.d("실패", "실패")
                        Toast.makeText(
                            this@LoginActivity,
                            "아이디와 비밀번호를 확인해주세요.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            })
        }
    }
}