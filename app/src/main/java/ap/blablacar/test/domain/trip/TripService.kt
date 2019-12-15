package ap.blablacar.test.domain.trip

import io.reactivex.Single

class TripService(private val repository: TripRepository) {
    fun getTrips(from: String, to: String, page: Int): Single<Trips> =
        repository.getTrips(from, to, page)
}