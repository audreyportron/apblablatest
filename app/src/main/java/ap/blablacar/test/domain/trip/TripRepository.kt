package ap.blablacar.test.domain.trip

import io.reactivex.Single

interface TripRepository {
    fun getTrips(from: String, to: String, page: Int): Single<Trips>
}