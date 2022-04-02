package com.example.android_week4
import kotlinx.parcelize.Parcelize
import android.os.Parcelable
@Parcelize
class User(var fullname : String?, var email:String?, var phone : String?) : Parcelable {
}