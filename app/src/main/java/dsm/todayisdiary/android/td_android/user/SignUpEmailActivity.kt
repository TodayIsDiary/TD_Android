package dsm.todayisdiary.android.td_android.user

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import dsm.todayisdiary.android.td_android.R
import dsm.todayisdiary.android.td_android.data.RetrofitBuilder
import dsm.todayisdiary.android.td_android.data.request.user.UserEmailRequest
import dsm.todayisdiary.android.td_android.databinding.ActivitySignupEmailBinding
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpEmailActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupEmailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupEmailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val retrofitBuilder = RetrofitBuilder

        val accountId = intent.getStringExtra("account_id")
        val password = intent.getStringExtra("password")

        binding.email.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (binding.email.length() > 6) {
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
                "email",
                "email ${binding.email.text}"
            )
            retrofitBuilder.userApi.userEmail(
                UserEmailRequest(
                    binding.email.text.toString()
                )
            ).enqueue(object : Callback<ResponseBody> {
                override fun onFailure(Call: Call<ResponseBody>, t: Throwable) {
                    Log.d("실패", "${t.message}")
                }

                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    if (response.isSuccessful) {
                        val intent =
                            Intent(this@SignUpEmailActivity, SignUpCodeActivity::class.java)
                        intent.putExtra("account_id", accountId)
                        intent.putExtra("password", password)
                        intent.putExtra("email", binding.email.text.toString())
                        startActivity(intent)
                    }
                }
            })

        }
    }
}