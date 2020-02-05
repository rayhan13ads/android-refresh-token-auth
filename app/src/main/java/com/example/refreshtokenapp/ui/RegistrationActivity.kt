package com.example.refreshtokenapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.refreshtokenapp.R
import com.example.refreshtokenapp.models.Registration
import com.example.refreshtokenapp.repository.AuthRepository

class RegistrationActivity : AppCompatActivity() {

    lateinit var catId:EditText
    lateinit var firstName:EditText
    lateinit var lastName:EditText
    lateinit var email:EditText
    lateinit var passwrod:EditText
    lateinit var repeatPassword:EditText
    lateinit var registrationBtn:Button

    val TAG = this.javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        initWidget()

        registrationBtn.setOnClickListener({
            createRegistration()
        })
    }

    fun goToLogin(){
        var intent = Intent(this,LoginActivity::class.java)
        startActivity(intent)
    }

    fun initWidget(){
        catId = findViewById(R.id.cat_id)
        firstName = findViewById(R.id.first_nameId)
        lastName = findViewById(R.id.last_nameId)
        email = findViewById(R.id.emailId)
        passwrod = findViewById(R.id.passwordId)
        repeatPassword = findViewById(R.id.repeate_passwordId)
        registrationBtn = findViewById(R.id.registrationBtn)
    }


    fun createRegistration(){

        var categoryId = catId.text.toString().toInt()
        var fname = firstName.text.toString()
        var lname = lastName.text.toString()
        var mail = email.text.toString()
        var pass = passwrod.text.toString()
        var rPass = repeatPassword.text.toString()

        var registration = Registration(categoryId,
            first_name = fname,
            last_name = lname,
            email = mail,
            password = pass,
            repeatPassword = rPass
            )

        val auth = AuthRepository(context = this)

        auth.registration(registration,{
            Toast.makeText(this, "Success : ${it.message}", Toast.LENGTH_LONG)

            if (it.success){
                goToLogin()
            }
        })
    }
}
