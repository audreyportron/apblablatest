package ap.blablacar.test.domain.oauth

import ap.blablacar.test.data.oauth.OAuthResponseJson
import retrofit2.Call

interface OAuthRepository {
    fun getToken(clientId: String, clientKey: String, grantType: String): Call<OAuthResponseJson>
    fun getSavedToken(): OAuthResponseJson?
    fun saveToken(oAuthResponseJson: OAuthResponseJson)
    fun deleteToken()
}