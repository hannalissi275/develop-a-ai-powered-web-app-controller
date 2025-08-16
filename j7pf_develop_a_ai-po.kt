Kotlin
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.tensorflow.Graph
import org.tensorflow.Output
import org.tensorflow.Session
import org.tensorflow.Tensor

@SpringBootApplication
@RestController
class J7pfDevelopAAiPoApplication {

    @GetMapping("/predict")
    fun predict(): String {
        val graph = Graph()
        val output = Output(graph, "output")
        val session = Session(graph)

        val input = Tensor.create(floatArrayOf(1.0f, 2.0f, 3.0f, 4.0f, 5.0f, 6.0f, 7.0f, 8.0f, 9.0f, 10.0f))

        val runner = session.runner()
        runner.feed("input", input)
        val outputTensor = runner.fetch(output).run().get(0)

        val outputArray = outputTensor.floatValue()
        val result = outputArray.joinToString(prefix = "[", postfix = "]")

        return "Prediction: $result"
    }

    @Bean
    fun corsConfigurer(): WebMvcConfigurer {
        return object : WebMvcConfigurer {
            override fun addCorsMappings(registry: CorsRegistry) {
                registry.addMapping("/**")
                    .allowedOrigins("*")
                    .allowedMethods("*")
            }
        }
    }
}

fun main(args: Array<String>) {
    runApplication<J7pfDevelopAAiPoApplication>(*args)
}