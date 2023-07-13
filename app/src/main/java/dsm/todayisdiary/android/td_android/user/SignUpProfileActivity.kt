package dsm.todayisdiary.android.td_android.user

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import dsm.todayisdiary.android.td_android.data.RetrofitBuilder
import dsm.todayisdiary.android.td_android.data.enums.SexType
import dsm.todayisdiary.android.td_android.data.request.user.UserSignUpRequest
import dsm.todayisdiary.android.td_android.databinding.ActivitySignupProfileBinding
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupProfileBinding

    private val GALLERY = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val retrofitBuilder = RetrofitBuilder

        val accountID = intent.getStringExtra("account_id")
        val password = intent.getStringExtra("password")
        val email = intent.getStringExtra("email")
        val imageUrl: String? = null
        var sex = SexType.FEMALE

        binding.imageUrl1.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent, "Load Picture"), GALLERY)
        }
        binding.imageUrl2.setOnClickListener {
            val intent: Intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.setType("image/*")
            startActivityForResult(intent, GALLERY)
        }

        binding.sexSwitch.setOnCheckedChangeListener { _, onSwitch ->
            sex = if (onSwitch) {
                SexType.MALE
            } else {
                SexType.FEMALE
            }
        }

        binding.finishButton.setOnClickListener {
            Log.d(
                "Sign",
                "id = ${accountID} \nnickname = ${binding.nickName.text} \nemail = ${email} \npw = ${password} \nimageUrl = ${imageUrl} \nsex = ${sex} \nintroduction = ${binding.introduction.text}"
            )
            retrofitBuilder.userApi.userSignUp(
                UserSignUpRequest(
                    accountID.toString(),
                    binding.nickName.text.toString(),
                    email.toString(),
                    password.toString(),
                    imageUrl,
                    sex,
                    binding.introduction.text.toString()
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
                        Log.d("성공", response.body()!!.toString())
                    }
                }
            })
            startActivity(Intent(this@SignUpProfileActivity, MainActivity::class.java));
            finish()
        }

    }

    @Override
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == GALLERY) {
            if (resultCode == RESULT_OK) {
                val dataUri = data?.data
                try {
                    var bitmap: Bitmap =
                        MediaStore.Images.Media.getBitmap(this.contentResolver, dataUri)
                    binding.imageUrl1.setImageBitmap(bitmap)
                } catch (e: Exception) {
                    Toast.makeText(this, "$e", Toast.LENGTH_SHORT).show()
                }
            }
        } else {

        }
    }
}