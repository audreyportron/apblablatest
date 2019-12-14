package ap.blablacar.test.ui.trip

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import ap.blablacar.test.domain.trip.Trip
import ap.blablacar.test.domain.trip.TripRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign


class TripDataSource(
    private val from: String,
    private val to: String,
    private val repository: TripRepository,
    private val compositeDisposable: CompositeDisposable
) : PageKeyedDataSource<Int, Trip>() {


    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Trip>
    ) {
        compositeDisposable += repository.getTrips(from, to, 1)
            .subscribe({ trips -> callback.onResult(trips.trips, 0, 2) }, {})
    }


    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Trip>) {
        compositeDisposable += repository.getTrips(from, to, params.key)
            .subscribe({ trips -> callback.onResult(trips.trips, (params.key + 1)) }, {})

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Trip>) {
    }

}

class TripDataSourceFactory(
    private val from: String,
    private val to: String,
    private val repository: TripRepository,
    private val compositeDisposable: CompositeDisposable
) : DataSource.Factory<Int, Trip>() {


    val tripsDataSourceLiveData = MutableLiveData<TripDataSource>()

    override fun create(): DataSource<Int, Trip> {
        val tripDataSource = TripDataSource(
            from,
            to,
            repository,
            compositeDisposable
        )
        tripsDataSourceLiveData.postValue(tripDataSource)
        return tripDataSource
    }

}