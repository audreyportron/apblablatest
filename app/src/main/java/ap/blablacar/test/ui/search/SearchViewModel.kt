package ap.blablacar.test.ui.search

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ap.blablacar.test.R

class SearchViewModel(val callBack: Listener) : ViewModel() {

    val to = MutableLiveData<String>()
    val from = MutableLiveData<String>()

    val error = ObservableField<Int>()


    interface Listener {
        fun searchForNewTrip(from: String, to: String)
    }

    fun find() {
        from.value?.let { from ->

            to.value?.let { to ->
                callBack.searchForNewTrip(from, to)
            } ?: error.set(R.string.search_error_to)
        } ?: error.set(R.string.search_error_from)

    }

    companion object {
        const val CALLBACK_PROPERTY = "callbackProperty"
    }

}