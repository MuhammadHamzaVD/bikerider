package com.example.bike.riders.feature.login.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.bike.riders.R
import com.example.bike.riders.feature.login.contracts.LoginInteractor
import com.example.bike.riders.feature.login.contracts.LoginPresenter
import com.example.bike.riders.feature.login.contracts.LoginView
import com.example.bike.riders.feature.login.presenter.LoginPresenterImpl
import com.example.bike.riders.feature.main.view.MainActivity
import com.example.bike.riders.feature.signup.view.SignupActivity
import dagger.android.DaggerActivity
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject

class LoginActivity :DaggerActivity(), LoginView{

    private lateinit var presenter: LoginPresenter
    @Inject
    lateinit var loginInteractor: LoginInteractor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        presenter = LoginPresenterImpl(this,loginInteractor)


        btnsign.setOnClickListener{
            startActivity(Intent(this@LoginActivity, SignupActivity::class.java))
            finish()
        }

        btnLogin.setOnClickListener {
            presenter.checkValidation(editEmail.text.toString(),editPassword.text.toString())
        }
    }

    override fun validatationError(){
        Toast.makeText(this@LoginActivity, "User Not Found", Toast.LENGTH_SHORT).show()
    }

    override fun invalidateEmail() {
        Toast.makeText(this@LoginActivity, "Incorrect Email", Toast.LENGTH_SHORT).show()
    }

    override fun invalidatePassword() {
        Toast.makeText(this@LoginActivity, "Incorrect Password", Toast.LENGTH_SHORT).show()
    }

    override fun emptyEmailField() {
        Toast.makeText(this@LoginActivity, "Email Field is Empty", Toast.LENGTH_SHORT).show()
    }

    override fun emptyPasswordField() {
        Toast.makeText(this@LoginActivity, "Password Field is Empty", Toast.LENGTH_SHORT).show()
    }

    override fun navigateToMainScreen() {
        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
        finish()
    }
}