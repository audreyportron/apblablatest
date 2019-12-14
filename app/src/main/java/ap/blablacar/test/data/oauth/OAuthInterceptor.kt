package ap.blablacar.test.data.oauth

import ap.blablacar.test.BuildConfig
import ap.blablacar.test.domain.oauth.OAuthRepository
import okhttp3.Interceptor
import okhttp3.Response

class OAuthInterceptor(val oAuthRepository: OAuthRepository) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var currentToken = oAuthRepository.getSavedToken()
        var response = chain.proceed(chain.request())

        if (currentToken == null) {
            response = getNewToken(chain)
        } else {

            val request = chain
                .request()
                .newBuilder()
                .addHeader(
                    "Authorization",
                    //"${currentToken.tokenType} // This doesn't Work since tokenType doesn't have the first char in upper case ;)
                    "Bearer ${currentToken?.token}"
                )
                .build()

            response = chain.proceed(request)

        }
        when (response.code()) {
            401 -> {
                oAuthRepository.deleteToken()
                getNewToken(chain)
            }
        }

        return response
    }

    fun getNewToken(chain: Interceptor.Chain): Response {

        var response = chain.proceed(chain.request())

        val newToken = oAuthRepository.getToken(
            BuildConfig.OAUTH_ID,
            BuildConfig.OAUTH_SECRET_ID,
            BuildConfig.OAUTH_GRANT
        ).execute()

        if (newToken.isSuccessful) {

            val currentToken = newToken.body()
            currentToken?.let {
                oAuthRepository.saveToken(it)

                val request = chain
                    .request()
                    .newBuilder()
                    .addHeader(
                        "Authorization",
                        //"${currentToken.tokenType} // This doesn't Work since tokenType doesn't have the first char in upper case ;)
                        "Bearer ${currentToken.token}"
                    )
                    .build()

                response = chain.proceed(request)


            }
        }
        return response
    }


}
