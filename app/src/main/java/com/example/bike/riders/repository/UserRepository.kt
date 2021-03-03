package com.example.bike.riders.repository

import com.example.bike.riders.entity.User

object UserRepository {

    const val USER = "USER"

    interface Repo{
        fun getUser(): User?
        fun saveUser(email: String, password: String): Boolean
        fun deleteUser(): Boolean
    }
}
