package ap.blablacar.test.data.oauth

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface OAuthApi {


    @POST("token")
    fun getToken(
        @Body oAuthRequestJson: OAuthRequestJson
    ): Call<OAuthResponseJson>
}