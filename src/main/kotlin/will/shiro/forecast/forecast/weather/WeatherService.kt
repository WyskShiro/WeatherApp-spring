package will.shiro.forecast.forecast.weather

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import will.shiro.forecast.forecast.firebase.FirestoreInstance

private const val COL_WEATHER = "weather"

@Service
class WeatherService @Autowired constructor(
    private val firestoreInstance: FirestoreInstance,
    private val weatherProvider: WeatherProvider
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

    fun getWeather(city: String, lat: Double, lon: Double): Mono<Weather>? {
        return Mono.fromCallable {
            firestoreInstance.get()
                .collection(COL_WEATHER)
                .document(city)
                .get()
                .get()
        }.flatMap {
            try {
                it.toObject(Weather::class.java)?.let {
                    Mono.just(it)
                } ?: weatherProvider.getWeather(lat, lon)
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }
}