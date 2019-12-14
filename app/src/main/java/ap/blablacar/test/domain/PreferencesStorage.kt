package ap.blablacar.test.domain

interface PreferencesStorage {

    fun contains(key: String): Boolean
    fun get(key: String): String?
    fun getLong(key: String): Long
    fun getBoolean(key: String): Boolean
    fun save(key: String, value: String)
    fun save(key: String, value: Long)
    fun save(key: String, value: Boolean)
    fun remove(key: String)
    fun getSet(key: String): Set<String>
    fun addToSet(key: String, value: String)
}

