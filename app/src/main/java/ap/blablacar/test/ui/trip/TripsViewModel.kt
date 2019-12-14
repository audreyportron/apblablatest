package ap.blablacar.test.ui.trip

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import ap.blablacar.test.domain.trip.Trip
import ap.blablacar.test.domain.trip.TripRepository
import io.reactivex.disposables.CompositeDisposable

class TripsViewModel(
    private val repository: TripRepository,
    private val from: String,
    private val to: String
) : ViewModel() {

    companion object {
        const val FROM_ID = "From_ID"
        const val TO_ID = "To_ID"
    }


    var tripsList: LiveData<PagedList<Trip>>
    private val pageSize = 5
    private var sourceFactory: TripDataSourceFactory
    private val compositeDisposable = CompositeDisposable()

    init {
        sourceFactory = TripDataSourceFactory(from, to, repository, compositeDisposable)

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