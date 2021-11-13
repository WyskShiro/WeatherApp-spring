package will.shiro.forecast.forecast.weather

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import will.shiro.forecast.forecast.firebase.FirestoreInstance

private const val COL_WEATHER = "weather"

@Service
class WeatherService @Autowired constructor(
    private val firestoreInstance: FirestoreInstance
) {

    fun saveWeather(weather: Weather): String {
        return firestoreInstance.get()
            .collection(COL_WEATHER)
            .document(weather.city)
            .set(weather)
            .get()
            .updateTime
            .toString()
    }

    fun getWeather(city: String): Weather? {
        val snapshot = firestoreInstance.get()
            .collection(COL_WEATHER)
            .document(city)
            .get()
            .get()

        return try {
            snapshot.toObject(Weather::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}