package ap.blablacar.test.domain.trip

data class Trips(
    val pager: Pager,
    val trips: List<Trip>
)

data class Pager(
    val page: Int,
    val pages: Int,
    val total: Int,
    val limit: Int
)

data class Trip(
    val user: User,
    val departureDate: String,
    val departurePlace: Place,
    val arrivalPlace: Place,
    val priceWithCommission: Price
)

data class User(
    val picture: String?,
    val displayedName: String
)

data class Place(val cityName: String)

data class Price(val stringValue: String)
