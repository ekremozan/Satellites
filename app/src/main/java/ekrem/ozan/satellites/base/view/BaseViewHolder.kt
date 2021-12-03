package ekrem.ozan.satellites.base.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<in T>(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(data: T)
}

