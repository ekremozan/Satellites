package ekrem.ozan.satellites.base.extensions

import java.text.DecimalFormat

fun Int?.orZero(): Int = this ?: 0
fun Int?.toDottedFormat(): String = DecimalFormat("###,###,###").format(this).replace(",",".")
