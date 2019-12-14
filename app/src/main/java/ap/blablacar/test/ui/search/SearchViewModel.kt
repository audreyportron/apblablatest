package ap.blablacar.test.ui.search

import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ap.blablacar.test.R

class SearchViewModel : ViewModel() {

    val to = MutableLiveData<String>()
    val from = MutableLiveData<String>()

    val error = ObservableField<Int>()


    interface Listener {
        fun searchForNewTrip(to: String, from: String)
    }

    var listener: Listener? = null

    fun find(v: View) {
        from.value?.let { from ->

            to.value?.let { to ->
                listener?.searchForNewTrip(from, to)
            } ?: error.set(R.string.search_error_to)
        } ?: error.set(R.string.search_error_from)

    }

}