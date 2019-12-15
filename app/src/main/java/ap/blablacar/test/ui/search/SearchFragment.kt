package ap.blablacar.test.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ap.blablacar.test.R
import ap.blablacar.test.databinding.SearchFragmentBinding
import ap.blablacar.test.ui.trip.TripsFragment
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.android.inject

class SearchFragment : Fragment(), SearchViewModel.Listener {

    lateinit var bindings: SearchFragmentBinding
    val model: SearchViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindings = DataBindingUtil.inflate(inflater, R.layout.search_fragment, container, false)
        getKoin().setProperty(SearchViewModel.CALLBACK_PROPERTY, this)
        bindings.model = model

        return bindings.root
    }

    override fun searchForNewTrip(from: String, to: String) {

        val bundle = bundleOf(TripsFragment.FROM_EXTRA to from, TripsFragment.TO_EXTRA to to)
        findNavController().navigate(
            R.id.trips_fragment, bundle
        )
    }

}