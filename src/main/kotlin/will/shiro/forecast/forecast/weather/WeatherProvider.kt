package will.shiro.forecast.forecast.weather

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.ExchangeFilterFunction
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Component
class WeatherProvider {

    fun getWeather(latitude: Double, longitude: Double): Mono<Weather> {
        val webClient = WebClient.builder()
            .filter(logRequest())
            .baseUrl("https://api.hgbrasil.com")
            .build()

        return webClient.get()
            .uri {
                it.path("/weather")
                    .queryParam("key", "234c865a")
                    .queryParam("lat", latitude)
                    .queryParam("lon", longitude)
                    .build()
            }
            .retrieve().bodyToMono(Weather::class.java).doOnError {
                println(it)
            }
    }

    fun logRequest(): ExchangeFilterFunction {
        return ExchangeFilterFunction.ofRequestProcessor {
            println(it.url())
            Mono.just(it)
        }
    }
}