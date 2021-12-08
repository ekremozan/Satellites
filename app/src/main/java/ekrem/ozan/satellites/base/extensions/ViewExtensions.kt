package ekrem.ozan.satellites.base.extensions

import android.content.Context
import android.graphics.drawable.Drawable
import android.graphics.drawable.InsetDrawable
import android.view.View
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import ekrem.ozan.satellites.R

fun SearchView.textChangeListener(clickedBlock: (String?) -> Unit) {
    setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            clickedBlock(newText)
            return true
        }
    })
}

fun RecyclerView.addVerticalDivider(context: Context, @DrawableRes item: Int, insetLeft: Int = 0, insetTop: Int = 0, insetRight: Int = 0, insetBottom: Int = 0){
    val divider = ContextCompat.getDrawable(context, item)
    val itemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
    val insetDivider = InsetDrawable(divider, insetLeft, insetTop, insetRight, insetBottom)
    itemDecoration.setDrawable(insetDivider)
    addItemDecoration(itemDecoration)
}

@BindingAdapter("isVisible")
fun View.isVisible(isVisible: Boolean) {
    visibility = when (isVisible) {
        true -> View.VISIBLE
        false -> View.GONE
    }
}
