package ap.blablacar.test.domain.trip

import ap.blablacar.test.data.trip.TripsJson
import io.reactivex.Single

interface TripRepository {
    fun getTrips(from: String, to: String, page: Int): Single<TripsJson>
}