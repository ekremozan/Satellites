package ekrem.ozan.satellites.util

import android.content.Context
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import ekrem.ozan.satellites.domain.model.SatelliteData
import java.io.IOException
import java.io.InputStream
import com.google.gson.reflect.TypeToken
import ekrem.ozan.satellites.ui.list.adapter.SatelliteAdapterCallBack
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.lang.reflect.Type


class JsonHelper {
    companion object{
        fun parseJsonFile(context: Context, fileName: String): String?{
             return try {
                val inputStream: InputStream = context.assets.open(fileName)
                val size: Int = inputStream.available()
                val buffer = ByteArray(size)
                inputStream.read(buffer)
                inputStream.close()
                String(buffer, Charsets.UTF_8)
            } catch (e: IOException) {
                e.printStackTrace()
                return null
            }
        }
    }
}