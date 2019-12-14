package ap.blablacar.test.ui.trip

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ap.blablacar.test.R
import ap.blablacar.test.domain.trip.Trip
import kotlinx.android.synthetic.main.trip_item.view.*

class TripsPagedAdapter() :
    PagedListAdapter<Trip, RecyclerView.ViewHolder>(tripDiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return TripViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as TripViewHolder).bindTo(getItem(position))
    }

    companion object {
        val tripDiffCallback = object : DiffUtil.ItemCallback<Trip>() {
            override fun areItemsTheSame(oldItem: Trip, newItem: Trip): Boolean {
                return oldItem.user.displayedName == newItem.user.displayedName
            }

            override fun areContentsTheSame(oldItem: Trip, newItem: Trip): Boolean {
                return oldItem == newItem
            }
        }
    }
}

class TripViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.trip_item, parent, false)
) {
    fun bindTo(trip: Trip?) {
        itemView.apply {
            trip_user_name.text = trip?.user?.displayedName ?: ""
//
//            house_item_region.text = trip.departurePlace.cityName
//            house_item_title.text = trip.arrivalPlace.cityName
        }

    }
}