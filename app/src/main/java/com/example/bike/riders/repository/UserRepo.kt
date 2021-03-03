package com.example.bike.riders.repository

import android.content.SharedPreferences
import com.example.bike.riders.entity.User
import com.example.bike.riders.extension.toGson
import com.example.bike.riders.extension.toSerializedObject
import javax.inject.Inject

class UserRepo @Inject constructor() : UserRepository.Repo {

    @Inject lateinit var preferences: SharedPreferences

    override fun getUser(): User? {
        val userString = preferences.getString(UserRepository.USER, null) ?: return null
        return userString.toSerializedObject()
    }

    override fun saveUser(email: String, password: String): Boolean {
        fun toBusiness() = User(email, password)
        preferences.edit().putString(UserRepository.USER, toBusiness().toGson()).apply()
        return true
    }

    override fun deleteUser(): Boolean {
        preferences.edit().remove(UserRepository.USER).apply()
        return true
    }
}