package com.example.fgt2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.activity_sign_up.password
import kotlinx.android.synthetic.main.custom_view.*

class SignUp : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        register_button.setOnClickListener {
            performRegister()
        }
    }

    fun showAlert(v: View) {
        val inflater = layoutInflater
        val inflate_view = inflater.inflate(R.layout.custom_view, null)

        val userNameEdt = inflate_view.findViewById(R.id.userName) as EditText
        val userPasswordEdt = inflate_view.findViewById(R.id.password) as EditText

        val checkBoxToggle = inflate_view.findViewById(R.id.showPassCheck) as CheckBox

        checkBoxToggle.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                userPasswordEdt.transformationMethod = null
            } else {
                userPasswordEdt.transformationMethod = PasswordTransformationMethod.getInstance()
            }
        }
        val dialogBox = AlertDialog.Builder(this)

//        set the title of the alert dialog
        dialogBox.setTitle("Login....")

//        set custom_view.xml in the alert dialog
        dialogBox.setView(inflate_view)
        dialogBox.setCancelable(false)

        dialogBox.setNegativeButton("Cancel") { dialog, which ->
            Toast.makeText(this, "Login cancelled", Toast.LENGTH_LONG).show()
        }
        dialogBox.setPositiveButton("Login") { dialog, which ->
            val email = userNameEdt.text.toString()
            val password = userPasswordEdt.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in the fields above", Toast.LENGTH_SHORT).show()
                return@setPositiveButton
            }

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email,password)
                .addOnCompleteListener {
                    if (!it.isSuccessful) return@addOnCompleteListener
                    //else if successful
                    Log.d(
                        "Signup",
                        "Successfully Logged in:${it.result?.user?.uid} "
                    )
                    Toast.makeText(this, "Successfully logged in", Toast.LENGTH_SHORT).show()

                }
                .addOnFailureListener {
                    if (password != password)
                    Log.d("Signup", "Failed to login:${it.message}")
                    Toast.makeText(this, "Failed to log in,user does not exist.Try registering", Toast.LENGTH_SHORT)
                        .show()
                }

        }
        val dialog = dialogBox.create()
        dialog.show()
    }

    private fun performRegister() {
        val email = email_register.text.toString()
        val password = password.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill in the fields above", Toast.LENGTH_SHORT).show()
            return
        }

        Log.d("RegisterActivity", "Email is: " + email)
        Log.d("RegisterActivity", "Password is: " + password)

        //Firebase Authentication to create user with email and password
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (!it.isSuccessful) return@addOnCompleteListener
                //else if successful
                Log.d(
                    "RegisterActivity",
                    "Successfully created user with uid:${it.result?.user?.uid} "
                )
                Toast.makeText(this, "Successfully created user", Toast.LENGTH_SHORT).show()
                finish()

            }
            .addOnFailureListener {
                Log.d("Register", "Failed to create user:${it.message}")
                Toast.makeText(this, "Failed to create user:${it.message}", Toast.LENGTH_SHORT).show()
            }
}
}
