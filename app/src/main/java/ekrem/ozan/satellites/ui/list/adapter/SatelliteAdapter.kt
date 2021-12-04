package ekrem.ozan.satellites.ui.list.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import ekrem.ozan.satellites.databinding.ItemSatelliteBinding
import ekrem.ozan.satellites.domain.model.SatelliteData
import ekrem.ozan.satellites.ui.list.adapter.viewholder.SatelliteViewHolder

class SatelliteAdapter(
    private val callback: SatelliteAdapterCallBack
) : ListAdapter<SatelliteData, SatelliteViewHolder>(
    AdapterItemDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SatelliteViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return SatelliteViewHolder(
            binding = ItemSatelliteBinding.inflate(layoutInflater, parent, false),
            callback = callback
        )
    }

    override fun onBindViewHolder(holder: SatelliteViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private class AdapterItemDiffCallback : DiffUtil.ItemCallback<SatelliteData>() {

        override fun areItemsTheSame(oldItem: SatelliteData, newItem: SatelliteData): Boolean {
            return oldItem.id == newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: SatelliteData, newItem: SatelliteData): Boolean {
            return oldItem == newItem
        }
    }
}
