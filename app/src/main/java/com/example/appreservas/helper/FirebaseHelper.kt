package com.example.appreservas.helper

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.example.appreservas.R

class FirebaseHelper {
    companion object{
        fun getDatabase()= FirebaseDatabase.getInstance().reference
        private fun getAuth()= FirebaseAuth.getInstance()
        fun getIdUser()= getAuth().uid

        fun getAuthenticated()= getAuth().currentUser !=null
        fun validError(error: String): Int{
            return when{
                error.contains("there is no user record corresponding to this identifier")-> {
                    R.string.account_not_registered_register_fragment
                }
                error.contains("The email address is badly formatted")-> {
                    R.string.invalid_email_register_fragment
                }
                error.contains("The passwords invalid or the user does not have a password")-> {
                    R.string.invalid_password_register_fragment
                }
                error.contains("The email address is already in use by another account")-> {
                    R.string.email_in_use_register_fragment
                }
                error.contains("Password should be at least 6 characteres")-> {
                    R.string.strong_password_register_fragment
                }
                else -> {
                    R.string.error_generic
                }
            }
        }
    }
}