package com.example.android_week4

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.regex.Pattern

class ViewModels : ViewModel() {
    private val _errEmail = MutableLiveData<String>()
    val errEmail : LiveData<String?> get() = _errEmail
    private val _errPassword = MutableLiveData<String>()
    val errPassword : LiveData<String?> get() = _errPassword
    private val _errName = MutableLiveData<String>()
    val errName : LiveData<String?> get() = _errName
    private var _isLogged = MutableLiveData<Boolean>()
    val isLogged : LiveData<Boolean> get() =  _isLogged

    fun checkSignup(email : String,password:String,name:String){
        if(checkEmail(email))
            _errEmail.postValue("")
        else {
            _errEmail.postValue("Invalid email")
        }
        if(checkPassword(password))
            _errPassword.postValue("")
        else {
            _errPassword.postValue("Invalid password")
        }
        if(!checkName(name))
            _errName.postValue("")
        else {
            _errName.postValue("Invalid name")
        }
    }

    private fun checkEmail(email:String):Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    private fun checkPassword(password: String):Boolean{
        return password.length in 8..10 && Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@\$%^&*-]).{8,}\$").matcher(password).matches()
    }
    private fun checkName(name: String):Boolean{
        return name.length <0
    }

    fun login(email:String,password:String) :Boolean{
        if(email == DataStore.email) {
            if(password != DataStore.password) {
                _errPassword.postValue("Invalid password")
                return false
            }
            else {
                _errPassword.postValue("")
                return true
            }
        }
        else {
            _errEmail.postValue("Invalid email")
            return false
        }

    }
}