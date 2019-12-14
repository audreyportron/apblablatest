package ap.blablacar.test.data.trip

import com.google.gson.annotations.SerializedName

data class TripsJson(
    @SerializedName("pager") val pager: PagerJson,
    @SerializedName("trips") val trips: List<TripJson>
)

data class PagerJson(
    @SerializedName("page") val page: Int,
    @SerializedName("pages") val pages: Int,
    @SerializedName("total") val total: Int,
    @SerializedName("limit") val limit: Int
)

data class TripJson(
    @SerializedName("user") val user: UserJson,
    @SerializedName("departure_date") val departureDate: String,
    @SerializedName("departure_place") val departurePlace: PlaceJson,
    @SerializedName("arrival_place") val arrivalPlace: PlaceJson,
    @SerializedName("price_with_commission") val priceWithCommission: PriceJson
)

data class UserJson(
    @SerializedName("picture") val picture: String?,
    @SerializedName("display_name") val displayedName: String
)

data class PlaceJson(@SerializedName("city_name") val cityName: String)

data class PriceJson(@SerializedName("string_value") val stringValue: String)
