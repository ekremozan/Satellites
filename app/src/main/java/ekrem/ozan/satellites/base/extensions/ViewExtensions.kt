package ekrem.ozan.satellites.base.extensions

import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.databinding.BindingAdapter

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

@BindingAdapter("isVisible")
fun View.isVisible(isVisible: Boolean) {
    visibility = when (isVisible) {
        true -> View.VISIBLE
        false -> View.GONE
    }
}
