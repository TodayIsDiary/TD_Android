package dsm.todayisdiary.android.td_android

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import dsm.todayisdiary.android.td_android.data.RetrofitBuilder
import dsm.todayisdiary.android.td_android.data.request.user.UserEmailRequest
import dsm.todayisdiary.android.td_android.databinding.ActivityLoginBinding
import dsm.todayisdiary.android.td_android.databinding.ActivitySignupBinding

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retrofitBuilder = RetrofitBuilder

        binding.sendButtonSign.setOnClickListener {
            Log.d(
                "email",
                "\nemail ${binding.emailSign.text.toString()}"
            )
            retrofitBuilder.userApi.userEmail(
                UserEmailRequest(binding.emailSign.text.toString())
            )
        }

        binding.nextButtonSign.setOnClickListener {
            val intent = Intent(this@SignUpActivity, SignUpProfileActivity::class.java)
            intent.putExtra("account_id", binding.accountIdSign.text.toString())
            startActivity(intent);
        }
    }
}