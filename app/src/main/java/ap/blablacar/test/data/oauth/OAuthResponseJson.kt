package ap.blablacar.test.data.oauth

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class OAuthResponseJson(
    @SerializedName("access_token") val token: String,
    @SerializedName("token_type") val tokenType: String
) : Parcelable


data class OAuthRequestJson(
    @SerializedName("client_id") val clientId: String,
    @SerializedName("client_secret") val clientSecret: String,
    @SerializedName("grant_type") val grantType: String
)