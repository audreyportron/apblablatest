package ap.blablacar.test.data.trip

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface TripApi {

    @GET("api/v2/trips?_format=json&locale=fr_FR&cur=EUR")
    fun getTrips(@Query("fn") from: String, @Query("tn") to: String, @Query("page") page: Int): Single<TripsJson>
}