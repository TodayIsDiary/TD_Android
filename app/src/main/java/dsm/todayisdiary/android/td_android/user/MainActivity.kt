package dsm.todayisdiary.android.td_android.user

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import dsm.todayisdiary.android.td_android.data.util.MyApplication
import dsm.todayisdiary.android.td_android.databinding.ActivityMainBinding
import dsm.todayisdiary.android.td_android.diary.HomeActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.todayisdiaryLogin.setOnClickListener {
            startActivity(Intent(this@MainActivity, LoginActivity::class.java))
        }
        binding.signup.setOnClickListener {
            startActivity(Intent(this@MainActivity, SignUpActivity::class.java))
        }
        binding.test.setOnClickListener {
            startActivity((Intent(this@MainActivity, HomeActivity::class.java)))
        }
    }

}