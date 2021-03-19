package com.khansafzh.themovieapp

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth
    private lateinit var refUsers: DatabaseReference
    private var firebaseUserID: String = ""

    companion object{
        fun getLaunchService (from: Context) = Intent(from, SignUpActivity::class.java).apply{
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val toolBar: Toolbar = findViewById(R.id.toolbar_signup)
        setSupportActionBar(toolBar)

        supportActionBar!!.title = getString(R.string.sign_in)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        toolBar.setNavigationOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
            finish()
        }
        mAuth = FirebaseAuth.getInstance()
        btn_register.setOnClickListener {
            signupUser()
        }
    }

    private fun signupUser() {
        val username: String = et_full_name_sign_up.text.toString()
        val email: String = et_email_sign_up.text.toString()
        val password: String = et_password_sign_up.text.toString()

        if (username == "") {
            Toast.makeText(this, getString(R.string.text_message_username), Toast.LENGTH_LONG)
                .show()

        } else if (email == "") {
            Toast.makeText(this, getString(R.string.text_message_email), Toast.LENGTH_LONG).show()

        } else if (password == "") {
            Toast.makeText(this, getString(R.string.text_message_password), Toast.LENGTH_LONG)
                .show()

        } else {

            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    firebaseUserID = mAuth.currentUser!!.uid
                    refUsers = FirebaseDatabase.getInstance()
                        .reference.child(getString(R.string.text_user)).child(firebaseUserID)

                    val userHashMap = HashMap<String, Any>()
                    userHashMap["uid"] = firebaseUserID
                    userHashMap["username"] = username
                    userHashMap["profile"] = "https://firebasestorage.googleapis.com/v0/b/cetup-app.appspot.com/o/profile.png?alt=media&token=2502475d-856d-477f-a2f4-f7c20803a2da"
                    userHashMap["cover"] = "https://firebasestorage.googleapis.com/v0/b/cetup-app.appspot.com/o/cover.jpg?alt=media&token=1e388f8a-4a4c-47bc-8892-2379e3f00ad6"
                    userHashMap["status"] = "offline"
                    userHashMap["search"] = username.toLowerCase()
                    userHashMap["facebook"] = "https://m.facebook.com"
                    userHashMap["instagram"] = "https://m.instagram.com"
                    userHashMap["website"] = "https://wwww.google.com"

                    refUsers.updateChildren(userHashMap).addOnCompleteListener {
                            task ->
                        if (task.isSuccessful){
                            val intent = Intent(this, MainActivity::class.java)
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(intent)
                            finish()
                        }
                    }
                } else {
                    Toast.makeText(
                        this, getString(R.string.text_error_message) + task.exception!!
                            .message.toString(), Toast.LENGTH_LONG
                    ).show()

                }
            }
        }
    }
}