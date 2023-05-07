package dsm.todayisdiary.android.td_android.user

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import dsm.todayisdiary.android.td_android.R
import dsm.todayisdiary.android.td_android.data.RetrofitBuilder
import dsm.todayisdiary.android.td_android.data.request.user.UserEmailCheckRequest
import dsm.todayisdiary.android.td_android.databinding.ActivitySignupCodeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpCodeActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupCodeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupCodeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val retrofitBuilder = RetrofitBuilder

        val accountId = intent.getStringExtra("account_id")
        val password = intent.getStringExtra("password")
        val email = intent.getStringExtra("email")

        var code = false

        binding.code.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (binding.code.length() >= 4) {
                    binding.nextButton.isEnabled = true
                    binding.nextButton.backgroundTintList = ColorStateList.valueOf(
                        ContextCompat.getColor(
                            applicationContext, R.color.white
                        )
                    )
                } else {
                    binding.nextButton.isEnabled = false
                    binding.nextButton.backgroundTintList = ColorStateList.valueOf(
                        ContextCompat.getColor(
                            applicationContext, R.color.button_default
                        )
                    )
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })

        binding.nextButton.setOnClickListener {
            Log.d(
                "code",
                "${binding.code.text}"
            )
            retrofitBuilder.userApi.userEmailCheck(
                UserEmailCheckRequest(
                    email.toString(),
                    binding.code.text.toString()
                )
            ).enqueue(object : Callback<Boolean> {
                override fun onFailure(call: Call<Boolean>, t: Throwable) {
                    Log.d("통신 실패", "${t.message}")
                }

                override fun onResponse(
                    call: Call<Boolean>,
                    response: Response<Boolean>
                ) {
                    if (response.isSuccessful) {
                        Log.d("id, pw", "$accountId \n $password")
                        val intent =
                            Intent(this@SignUpCodeActivity, SignUpProfileActivity::class.java)
                        intent.putExtra("account_id", accountId)
                        intent.putExtra("password", password)
                        intent.putExtra("email", email)
                        startActivity(intent)
                    } else {
                        binding.codeInputLayout.error = "인증코드가 일치하지 않습니다."
                    }
                }
            })
        }
    }
}