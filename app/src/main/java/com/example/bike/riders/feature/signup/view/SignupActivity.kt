package com.example.bike.riders.feature.signup.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.bike.riders.R
import com.example.bike.riders.feature.login.view.LoginActivity
import com.example.bike.riders.feature.main.view.MainActivity
import com.example.bike.riders.feature.signup.contracts.SignupInteractor
import com.example.bike.riders.feature.signup.contracts.SignupPresenter
import com.example.bike.riders.feature.signup.contracts.SignupView
import com.example.bike.riders.feature.signup.presenter.SignupPresenterImpl
import dagger.android.DaggerActivity
import kotlinx.android.synthetic.main.activity_signup.*
import javax.inject.Inject

class SignupActivity : DaggerActivity(), SignupView {

    private lateinit var presenter: SignupPresenter
    @Inject
    lateinit var signupInteractor: SignupInteractor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        presenter = SignupPresenterImpl(this,signupInteractor)

        btnSignup.setOnClickListener {
            presenter.checkValidation(signEmail.text.toString(),signPassword.text.toString(),confirmPassword.text.toString(), signUsername.text.toString(), signage.text.toString())
        }

        btnlog.setOnClickListener {
            startActivity(Intent(this@SignupActivity, LoginActivity::class.java))
            finish()
        }

    }

    override fun navigateToMainScreen() {
        startActivity(Intent(this@SignupActivity, MainActivity::class.java))
        finish()
    }

    override fun validatationError() {
        Toast.makeText(this@SignupActivity, "User Not Found", Toast.LENGTH_SHORT).show()
    }

    override fun invalidateEmail() {
        Toast.makeText(this@SignupActivity, "Invalid Email", Toast.LENGTH_SHORT).show()
    }

    override fun invalidatePassword() {
        Toast.makeText(this@SignupActivity, "Weak Password", Toast.LENGTH_SHORT).show()
    }

    override fun invalidateConfirm() {
        Toast.makeText(this@SignupActivity, "Password Doesn't Match", Toast.LENGTH_SHORT).show()
    }

    override fun invalidateUsername() {
        Toast.makeText(this@SignupActivity, "Incorrect UserName", Toast.LENGTH_SHORT).show()
    }

    override fun emptyEmailField() {
        Toast.makeText(this@SignupActivity, "Email Field is Empty", Toast.LENGTH_SHORT).show()
    }

    override fun emptyPasswordField() {
        Toast.makeText(this@SignupActivity, "Password Field is Empty", Toast.LENGTH_SHORT).show()
    }

    override fun emptyConfirmField() {
        Toast.makeText(this@SignupActivity, "Confirm Password Field is Empty", Toast.LENGTH_SHORT).show()
    }

    override fun emptyAgeField() {
        Toast.makeText(this@SignupActivity, "Age Field is Empty", Toast.LENGTH_SHORT).show()
    }

    override fun emptyUsernameField() {
        Toast.makeText(this@SignupActivity, "Username Field is Empty", Toast.LENGTH_SHORT).show()
    }
}