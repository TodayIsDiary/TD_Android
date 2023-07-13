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
import dsm.todayisdiary.android.td_android.databinding.ActivitySignupBinding
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.regex.Pattern

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val retrofitBuilder = RetrofitBuilder

        var id = false
        var pw = false
        var pwv = false

        binding.idCheckButton.setOnClickListener {
            Log.d(
                "accountId",
                "${binding.accountId.text}"
            )
            retrofitBuilder.userApi.userIdCheck(
                binding.accountId.text.toString()
            ).enqueue(object : Callback<Boolean> {
                override fun onFailure(call: Call<Boolean>, t: Throwable) {
                    Log.d("통신 실패", "${t.message}")
                }

                override fun onResponse(
                    call: Call<Boolean>,
                    response: Response<Boolean>
                ) {
                    if (response.isSuccessful) {
                        id = true
                        Toast.makeText(
                            this@SignUpActivity,
                            "아이디 중복체크가 완료되었습니다.",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        id = false
                        binding.accountIdInputLayout.error = "중복되는 아이디가 있습니다."
                    }
                }
            })
        }
        binding.accountId.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (binding.accountId.length() < 6) {
                    id = false
                    binding.accountIdInputLayout.error = "아이디는 최소 6자 이상입니다."
                } else {
                    binding.accountIdInputLayout.error = null
                }

            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })

        binding.password.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val pwPattern = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+${'$'}).{8,16}"
                val pattern = Pattern.compile(pwPattern)
                val matcher = pattern.matcher(binding.password.text)
                pw = matcher.find()
            }

            override fun afterTextChanged(p0: Editable?) {
                if (binding.password.length() < 8) {
                    binding.passwordInputLayout.error = "비밀번호는 최소 8 ~ 16자 입니다."
                } else if (!pw) {
                    binding.passwordInputLayout.error = "숫자와 특수문자를 사용해 주세요."
                } else {
                    binding.passwordInputLayout.error = null
                }
            }
        })

        binding.passwordValid.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int   , p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                if (binding.password.text.toString() == binding.passwordValid.text.toString()) {
                    pwv = true
                    binding.passwordValidInputLayout.error = null
                } else {
                    pwv = false
                    binding.passwordValidInputLayout.error = "비밀번호가 일치하지 않습니다."
                }
            }
        })

        binding.nextButton.setOnClickListener {
            if (id) {
                if (pw) {
                    if (pwv) {
                        val intent = Intent(this@SignUpActivity, SignUpEmailActivity::class.java)
                        intent.putExtra("account_id", binding.accountId.text.toString())
                        intent.putExtra("password", binding.password.text.toString())
                        startActivity(intent)
                    } else {
                        Toast.makeText(
                            this@SignUpActivity,
                            "비밀번호가 일치하지 않습니다.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(
                        this@SignUpActivity,
                        "비밀번호가 잘못되었습니다.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(
                    this@SignUpActivity,
                    "아이디가 중복되었습니다.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
