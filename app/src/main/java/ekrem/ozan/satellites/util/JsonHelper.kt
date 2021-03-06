package ekrem.ozan.satellites.util

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException
import java.io.InputStream
import java.lang.reflect.Type

class JsonHelper {
    companion object {
        fun parseJsonFile(context: Context, fileName: String): String? {
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

        inline fun <reified T> fromJson(text: String): T {
            val type: Type = object : TypeToken<T>() {}.type
            return Gson().fromJson(text, type)
        }
    }
}