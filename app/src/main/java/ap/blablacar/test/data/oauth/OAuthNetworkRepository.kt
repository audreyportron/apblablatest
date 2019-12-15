package ap.blablacar.test.data.oauth


import ap.blablacar.test.domain.PreferencesStorage
import ap.blablacar.test.domain.oauth.OAuthRepository
import retrofit2.Call

class OAuthNetworkRepository(
    private val oauthApi: OAuthApi,
    private val sharedPreferences: PreferencesStorage
) : OAuthRepository {
    override fun getSavedToken(): OAuthResponseJson? {
        val token = sharedPreferences.get(TOKEN_PREF)
        val tokenType = sharedPreferences.get(TOKEN_TYPE_PREF)
        if (token.isNullOrBlank() || tokenType.isNullOrBlank()) return null
        return OAuthResponseJson(token, tokenType)
    }

    override fun deleteToken() {
        sharedPreferences.remove(TOKEN_PREF)
        sharedPreferences.remove(TOKEN_TYPE_PREF)
    }

    override fun saveToken(oAuthResponseJson: OAuthResponseJson) {
        sharedPreferences.save(TOKEN_PREF, oAuthResponseJson.token)
        sharedPreferences.save(TOKEN_TYPE_PREF, oAuthResponseJson.tokenType)
    }

    override fun getToken(
        clientId: String,
        clientKey: String,
        grantType: String
    ): Call<OAuthResponseJson> {

        return oauthApi.getToken(OAuthRequestJson(clientId, clientKey, grantType))


    }

    companion object {
        const val TOKEN_PREF = "TOKEN_PREF"
        const val TOKEN_TYPE_PREF = "TOKEN_TYPE_PREF"

    }
}