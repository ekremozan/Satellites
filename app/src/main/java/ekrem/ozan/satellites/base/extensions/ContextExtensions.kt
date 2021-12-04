package ekrem.ozan.satellites.base.extensions

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.fragment.app.Fragment

fun Fragment.runContextNotNull(block: (Context) -> Unit) {
    this.context?.let {
        block(it)
    }
}