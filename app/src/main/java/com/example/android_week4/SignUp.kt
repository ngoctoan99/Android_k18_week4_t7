package com.example.android_week4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.android_week4.databinding.ActivitySignUpBinding
import kotlin.system.exitProcess

class SignUp : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var viewModel: ViewModels

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        viewModel = ViewModelProvider(this).get(ViewModels::class.java)
        binding.btnsign.setOnClickListener { onSubmit() }
        binding.editemail.addTextChangedListener { onChange() }
        binding.editfullname.addTextChangedListener { onChange() }
        binding.editpass.addTextChangedListener{onChange() }
    }
    private fun onChange(){
        val email = binding.editemail.text.toString()?.trim()
        val password = binding.editpass.text.toString()?.trim()
        val name = binding.editfullname.text.toString()?.trim()
        viewModel.checkSignup(email, password, name)
    }
    private fun onSubmit(){
        if(viewModel.errEmail.value == "" && viewModel.errName.value == "" && viewModel.errPassword.value == "") {
            DataStore.email = binding.editemail.text.toString().trim()
            DataStore.name = binding.editfullname.text.toString().trim()
            DataStore.password = binding.editpass.text.toString().trim()
            print(DataStore.password)
            Toast.makeText(this,"Đăng ký thành công .Mời bạn đăng nhập !!", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this,LoginIn::class.java))
            finish()
        }
        else {
            Toast.makeText(this,"${viewModel.errPassword.value} , ${viewModel.errEmail.value}, ${viewModel.errName.value}",Toast.LENGTH_SHORT).show()
        }
    }

}