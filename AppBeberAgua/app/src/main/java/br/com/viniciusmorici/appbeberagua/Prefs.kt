package br.com.viniciusmorici.appbeberagua

import android.content.SharedPreferences

object Prefs {

    val PREF_ID = "LMS"

    // retorna o armazém de preferências PREF_ID
    private fun prefs(): SharedPreferences {
        val context = LMSApplication.getInstance().applicationContext
        return context.getSharedPreferences(PREF_ID, 0)
    }

    fun setInt(flag: String, valor: Int) = prefs().edit().putInt(flag, valor).apply()

    fun getInt(flag: String) = prefs().getInt(flag, 0)

    fun setString(flag: String, valor: String) =  prefs().edit().putString(flag, valor).apply()

    fun getString(flag: String) = prefs().getString(flag, "0")!!
}
