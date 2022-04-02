package com.example.android_week4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.android_week4.databinding.ActivityLoginInBinding

class LoginIn : AppCompatActivity() {
    private lateinit var binding: ActivityLoginInBinding
    private lateinit var viewModel: ViewModels
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login_in)
        viewModel = ViewModelProvider(this).get(ViewModels::class.java)

        val btnLogin = binding.btnlogin
        binding.tvsignup.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }
        btnLogin.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                onSubmit()
            }
        })
    }

    private fun onSubmit() {
        var email = binding.editemail1.text.toString()?.trim()
        var password = binding.editpass1.text.toString()?.trim()
        if (viewModel.login(email, password)) {
            val acProfile: Intent = Intent(this, MainActivity::class.java)
            acProfile.putExtra("user", User(DataStore.name, DataStore.email, DataStore.phone))
            startActivity(acProfile)
            finish()
        } else Toast.makeText(this, "Đăng nhập thất bại", Toast.LENGTH_SHORT)
    }

    private fun Messeage() {
        viewModel.errEmail.observe(this) { errors ->
            val dialog = AlertDialog.Builder(this)
            dialog.setTitle("Lỗi Email")
            dialog.setMessage(errors)
            dialog.show()
        }
        viewModel.errPassword.observe(this) { errors ->
            val dialog = AlertDialog.Builder(this)
            dialog.setTitle("Lỗi PassWord")
            dialog.setMessage(errors)
            dialog.show()
        }
    }
}