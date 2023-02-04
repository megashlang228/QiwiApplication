package com.example.qiwiapplication.utils

import android.content.Context
import android.content.SharedPreferences
import com.example.qiwiapplication.QiwiApplication

object SharedPref{

    private val sharedPref: SharedPreferences = QiwiApplication.context.getSharedPreferences(PREF, Context.MODE_PRIVATE)

    fun saveToken(token: String){
        put(PREF_TOKEN, token)
    }

    fun getToken(): String {
        return get(PREF_TOKEN, String::class.java)
    }

    fun containsToken(): Boolean{
        return sharedPref.contains(PREF_TOKEN)
    }

    fun saveTokenPhone(token: String){
        put(PREF_TOKEN_PHONE, token)
    }

    fun getTokenPhone(): String {
        return get(PREF_TOKEN_PHONE, String::class.java)
    }

    fun containsTokenPhone(): Boolean{
        return sharedPref.contains(PREF_TOKEN_PHONE)
    }

    fun setIsSubscribeNotification(param: Boolean){
        put(PREF_SUBSCRIBE_NOTIFICATION, Boolean)
    }

    fun isSubscribeNotification(): Boolean {
        return get(PREF_SUBSCRIBE_NOTIFICATION, Boolean::class.java)
    }

    fun containsIsSubscribeNotification(): Boolean{
        return sharedPref.contains(PREF_SUBSCRIBE_NOTIFICATION)
    }

    fun savePersonId(personId: String){
        put(PREF_PERSON_ID, personId)
    }

    fun getPersonId(): String {
        return get(PREF_PERSON_ID, String::class.java)
    }

    fun containsPersonId(): Boolean{
        return sharedPref.contains(PREF_PERSON_ID)
    }

    private fun <T> get(key: String, clazz: Class<T>): T =
        when (clazz) {
            String::class.java -> sharedPref.getString(key, "")
            Boolean::class.java -> sharedPref.getBoolean(key, false)
            Float::class.java -> sharedPref.getFloat(key, -1f)
            Double::class.java -> sharedPref.getFloat(key, -1f)
            Int::class.java -> sharedPref.getInt(key, -1)
            Long::class.java -> sharedPref.getLong(key, -1L)
            else -> null
        } as T

    private fun <T> put(key: String, data: T) {
        val editor = sharedPref.edit()
        when (data) {
            is String -> editor.putString(key, data)
            is Boolean -> editor.putBoolean(key, data)
            is Float -> editor.putFloat(key, data)
            is Double -> editor.putFloat(key, data.toFloat())
            is Int -> editor.putInt(key, data)
            is Long -> editor.putLong(key, data)
        }
        editor.apply()
    }

    fun clearToken() {
        sharedPref.edit().run {
            remove(PREF_TOKEN)
        }.apply()
    }

    fun clearPersonId() {
        sharedPref.edit().run {
            remove(PREF_PERSON_ID)
        }.apply()
    }
}