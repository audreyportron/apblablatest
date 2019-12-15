package ap.blablacar.test.ui.trip

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import ap.blablacar.test.domain.trip.Trip
import ap.blablacar.test.domain.trip.TripService
import io.reactivex.disposables.CompositeDisposable

class TripsViewModel(
    private val service: TripService,
    private val from: String,
    private val to: String
) : ViewModel() {

    companion object {
        const val FROM_ID = "From_ID"
        const val TO_ID = "To_ID"
    }


    val noResult = ObservableBoolean(false)

    var tripsList: LiveData<PagedList<Trip>>
    private val pageSize = 10
    private var sourceFactory: TripDataSourceFactory
    private val compositeDisposable = CompositeDisposable()

    init {
        sourceFactory = TripDataSourceFactory(from, to, service, compositeDisposable)

        val config = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setInitialLoadSizeHint(pageSize + 1)
            .setEnablePlaceholders(false)
            .build()
        tripsList = LivePagedListBuilder<Int, Trip>(sourceFactory, config).build()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}