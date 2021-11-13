package will.shiro.forecast.forecast.weather

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class WeatherController @Autowired constructor(
    val weatherService: WeatherService
) {

    @GetMapping("/weather")
    suspend fun getWeather(@RequestParam("city") city: String) = coroutineScope {
        val weatherDeferred = async(start = CoroutineStart.LAZY) { weatherService.getWeather(city) }

        when (val weather = weatherDeferred.await()) {
            null -> ResponseEntity.status(HttpStatus.NOT_FOUND).build()
            else -> ResponseEntity.status(HttpStatus.OK).body(weather)
        }
    }

    @PostMapping("/weather")
    fun createWeather(@RequestBody weather: Weather): String {
        return weatherService.saveWeather(weather)
    }
}