package will.shiro.forecast.forecast.weather

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@RestController
class WeatherController @Autowired constructor(
    val weatherService: WeatherService
) {

    @GetMapping("/weather")
    fun getWeather(
        @RequestParam("city") city: String,
        @RequestParam("lat") lat: Double,
        @RequestParam("lon") lon: Double
    ): Mono<ResponseEntity<Weather>>? {
        return weatherService.getWeather(city, lat, lon)
            ?.map {
                when (it) {
                    null -> ResponseEntity.status(HttpStatus.NOT_FOUND).build()
                    else -> ResponseEntity.status(HttpStatus.OK).body(it)
                }
            }
    }

    @PostMapping("/weather")
    fun createWeather(@RequestBody weather: Weather): String {
        return weatherService.saveWeather(weather)
    }
}