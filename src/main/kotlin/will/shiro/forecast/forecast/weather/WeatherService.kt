package will.shiro.forecast.forecast.weather

import com.google.cloud.Timestamp
import com.google.firebase.cloud.FirestoreClient
import org.springframework.stereotype.Service

private const val COL_WEATHER = "weather"

@Service
class WeatherService {

    fun saveWeather(weather: Weather): String {
        return FirestoreClient.getFirestore()
            .collection(COL_WEATHER)
            .document(weather.city)
            .set(weather)
            .get()
            .updateTime
            .toString()
    }

}