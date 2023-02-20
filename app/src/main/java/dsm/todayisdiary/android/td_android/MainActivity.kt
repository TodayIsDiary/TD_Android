package dsm.todayisdiary.android.td_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dsm.todayisdiary.android.td_android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.todayisdiaryLoginMain.setOnClickListener {
            startActivity(Intent(this@MainActivity, LoginActivity::class.java));
        }
        binding.signupMain.setOnClickListener {
            startActivity(Intent(this@MainActivity, SignUpActivity::class.java));
        }
    }

}