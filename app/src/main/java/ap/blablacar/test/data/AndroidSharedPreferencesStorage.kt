package ap.blablacar.test.data

import android.content.Context
import android.content.SharedPreferences
import ap.blablacar.test.domain.PreferencesStorage


class AndroidSharedPreferencesStorage(context: Context) : PreferencesStorage {

    private val sharedPref: SharedPreferences =
        context.getSharedPreferences("", Context.MODE_PRIVATE)

    override fun get(key: String): String? {
        return sharedPref.getString(key, null)
    }

    override fun getLong(key: String): Long {
        return sharedPref.getLong(key, 0)
    }

    override fun getBoolean(key: String): Boolean {
        return sharedPref.getBoolean(key, false)
    }

    override fun save(key: String, value: String) {
        sharedPref.edit().putString(key, value).apply()
    }

    override fun save(key: String, value: Long) {
        sharedPref.edit().putLong(key, value).apply()
    }

    override fun save(key: String, value: Boolean) {
        sharedPref.edit().putBoolean(key, value).apply()
    }

    override fun contains(key: String): Boolean {
        return sharedPref.contains(key)
    }

    override fun remove(key: String) {
        sharedPref.edit().remove(key).apply()
    }

    override fun getSet(key: String): Set<String> =
        sharedPref.getStringSet(key, emptySet()) ?: emptySet()


    override fun addToSet(key: String, value: String) {
        val newSet = getSet(key) + value
        sharedPref.edit().putStringSet(key, newSet).apply()
    }


}
