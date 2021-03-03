package com.example.bike.riders.extension

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

inline fun <reified T> Gson.fromJson(json: String) = this.fromJson<T>(json, object: TypeToken<T>() {}.type)

inline fun <reified T> String.toSerializedObject(): T {
    return Gson().fromJson<T>(this)
}

fun Any.toGson(): String {
    return Gson().toJson(this)
}