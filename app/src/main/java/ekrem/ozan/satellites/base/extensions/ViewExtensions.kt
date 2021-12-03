package ekrem.ozan.satellites.base.extensions

import androidx.appcompat.widget.SearchView

fun SearchView.clickSubmitButton(clickedBlock: (String?) -> Unit) {
    setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            clickedBlock(query)
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            return true
        }
    })
}