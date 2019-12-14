package ap.blablacar.test.data.trip

import ap.blablacar.test.domain.trip.*

fun TripsJson.toTrip(): Trips = Trips(pager.toPager(), trips.map { it.toTrip() })

private fun TripJson.toTrip(): Trip = Trip(
    user = User(
        picture = this.user.picture,
        displayedName = this.user.displayedName
    ),
    departureDate = this.departureDate,
    departurePlace = Place(departurePlace.cityName),
    arrivalPlace = Place(arrivalPlace.cityName),
    priceWithCommission = Price(priceWithCommission.stringValue)
)

private fun PagerJson.toPager(): Pager = Pager(page, pages, total, limit)


