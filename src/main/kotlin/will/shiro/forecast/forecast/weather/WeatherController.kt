package will.shiro.forecast.forecast.weather

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class WeatherController {

    @Autowired
    lateinit var weatherService: WeatherService

    @GetMapping("/weather")
    fun getCurrentWeather(): String {
        return "Hello"
    }

    @PostMapping("/weather")
    fun createWeather(@RequestBody weather: Weather): String {
        return weatherService.saveWeather(weather)
    }
}