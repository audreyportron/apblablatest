package ap.blablacar.test.ui.trip

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ap.blablacar.test.R
import ap.blablacar.test.databinding.TripsFragmentBinding
import ap.blablacar.test.domain.trip.Trip
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.android.inject

class TripsFragment : Fragment() {

    val tripsViewModel: TripsViewModel by inject()

    private lateinit var bindings: TripsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindings = DataBindingUtil.inflate(inflater, R.layout.trips_fragment, container, false)



        getKoin().setProperty(TripsViewModel.FROM_ID, requireArguments().getString(FROM_EXTRA, ""))
        getKoin().setProperty(TripsViewModel.TO_ID, requireArguments().getString(TO_EXTRA, ""))
        bindings.model = tripsViewModel
        initAdapter()

        return bindings.root
    }


    private fun initAdapter() {

        val linearLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        val tripAdapter = TripsPagedAdapter()
        bindings.tripRecyclerView.apply {
            layoutManager = linearLayoutManager
            adapter = tripAdapter
        }
        tripsViewModel.tripsList.observe(this,
            Observer<PagedList<Trip>> {
                tripsViewModel.noResult.set(it.isEmpty())
                tripAdapter.submitList(it)
            })
    }

    companion object {
        const val FROM_EXTRA = "FROM_EXTRA"
        const val TO_EXTRA = "TO_EXTRA"
    }
}