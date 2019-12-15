package ap.blablacar.test.ui.trip

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ap.blablacar.test.R
import ap.blablacar.test.domain.trip.Trip
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
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
            trip_destination.text = context.getString(
                R.string.trip_destination,
                trip?.departurePlace?.cityName ?: "",
                trip?.arrivalPlace?.cityName ?: ""
            )
            trip_price.text = trip?.priceWithCommission?.stringValue


            Glide.with(trip_user_pic)
                .load(trip?.user?.picture ?: "")
                .apply(
                    RequestOptions().placeholder(R.drawable.ic_no_image)
                        .fallback(R.drawable.ic_no_image)
                        .error(R.drawable.ic_no_image)
                )
                .into(trip_user_pic)

        }

    }
}