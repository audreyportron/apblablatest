package ap.blablacar.test.data.trip

import com.nhaarman.mockitokotlin2.given
import io.reactivex.Single
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TripNetworkRepositoryTest {

    @Mock
    private lateinit var api: TripApi

    private val repository: TripNetworkRepository by lazy {
        TripNetworkRepository(api)
    }

    @Test
    fun should_get_Trip_json_and_return_trip_when_call_api() {
        //Given

        given(api.getTrips("", "", 1)).willReturn(Single.just(DataFactory.tripsJson))

        //When
        val test = repository.getTrips("", "", 1).test()

        //Then
        test.assertNoErrors()
            .assertValueAt(0) {
                it.trips.size == 1
                        && it.trips[0].user.displayedName == DataFactory.tripJson.user.displayedName
                        && it.trips[0].user.picture == DataFactory.tripJson.user.picture
                        && it.trips[0].departurePlace.cityName == DataFactory.tripJson.departurePlace.cityName
                        && it.trips[0].arrivalPlace.cityName == DataFactory.tripJson.arrivalPlace.cityName
                        && it.trips[0].priceWithCommission.stringValue == DataFactory.tripJson.priceWithCommission.stringValue
            }
    }


    @Throws(Throwable::class)
    @Test
    fun should_get_single_error_when_error_happen() {
        //Given
        val throwable = Throwable()
        given(api.getTrips("", "", 1)).willReturn(Single.error(throwable))

        //When
        //When
        val test = repository.getTrips("", "", 1).test()

        //Then
        test.assertError(throwable)

    }
}

object DataFactory {


    val tripJson = TripJson(
        UserJson(".jpg", "Audrey PORTRON"),
        departureDate = "2019",
        departurePlace = PlaceJson("Paris"),
        arrivalPlace = PlaceJson("Angers"),
        priceWithCommission = PriceJson("23,00€")
    )

    val tripsJson = TripsJson(
        PagerJson(1, 0, 0, 0),
        listOf(tripJson)
    )

}