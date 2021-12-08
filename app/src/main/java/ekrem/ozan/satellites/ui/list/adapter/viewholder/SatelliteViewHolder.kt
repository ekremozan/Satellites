package ekrem.ozan.satellites.ui.list.adapter.viewholder

import ekrem.ozan.satellites.base.view.BaseViewHolder
import ekrem.ozan.satellites.databinding.ItemSatelliteBinding
import ekrem.ozan.satellites.domain.model.SatelliteData
import ekrem.ozan.satellites.ui.list.SatelliteViewState

class SatelliteViewHolder(
    private val binding: ItemSatelliteBinding,
    private val onItemClickListener: (SatelliteData) -> Unit
) : BaseViewHolder<SatelliteData>(binding.root) {

    override fun bind(data: SatelliteData) {
        binding.viewState = SatelliteViewState(data)

        binding.root.setOnClickListener {
            onItemClickListener.invoke(data)
        }
    }
}
