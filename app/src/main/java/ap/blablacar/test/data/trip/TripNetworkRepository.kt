package ap.blablacar.test.data.trip

import ap.blablacar.test.domain.trip.TripRepository
import ap.blablacar.test.domain.trip.Trips
import io.reactivex.Single

class TripNetworkRepository(private val tripApi: TripApi) : TripRepository {
    override fun getTrips(from: String, to: String, page: Int): Single<Trips> {
        return tripApi.getTrips(from, to, page).map {
            it.toTrip()
        }
    }

}

