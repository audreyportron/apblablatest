package ap.blablacar.test.ui.search

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ap.blablacar.test.R
import ap.blablacar.test.app.AppSchedulers
import ap.blablacar.test.domain.trip.TripRepository

class SearchViewModel(val repository: TripRepository) : ViewModel() {

    val to = MutableLiveData<String>()
    val from = MutableLiveData<String>()

    val error = ObservableField<Int>()


    interface Listener {
        fun searchForNewTrip(to: String, from: String)
    }

    var listener: Listener? = null

    fun find() {
        from.value?.let { from ->

            to.value?.let { to ->
                listener?.searchForNewTrip(from, to)

                repository.getTrips("Paris", "Angers", 1)
                    .subscribeOn(AppSchedulers.io())
                    .observeOn(AppSchedulers.mainThread())
                    .subscribe({}, {})

            } ?: error.set(R.string.search_error_to)
        } ?: error.set(R.string.search_error_from)

    }

}