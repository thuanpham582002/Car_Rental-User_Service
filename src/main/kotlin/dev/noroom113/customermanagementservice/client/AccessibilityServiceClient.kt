package dev.noroom113.customermanagementservice.client

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.ResponseEntity
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.web.bind.annotation.GetMapping

@FeignClient(name = "accessibility-service", path = "/api/v1/accessibility")
interface AccessibilityServiceClient {

    @GetMapping("/all")
    fun getAccessibilities(): ResponseEntity<List<Accessibility>>
}

@Configuration
class AccessibilityResponseInterceptor(
    private val accessibilityServiceClient: AccessibilityServiceClient
){
    private var accessibilities: List<Accessibility> = emptyList()

    @Bean("accessibilities")
    fun getAccessibilities(): List<Accessibility> {
        return accessibilities
    }

    @Scheduled(fixedRate = 5000)
    fun getAccessibilitiesByScheduled() {
        accessibilityServiceClient.getAccessibilities().body?.let {
            accessibilities = it
        }
        println(accessibilities)
    }
}

data class Accessibility(
    val id: Long = 0,
    val name: String,
    val description: String,
    val urlAccessables: List<UrlAccessable> = emptyList(),
    val childAccessibilities: List<Accessibility> = emptyList(),
) {
    override fun toString(): String {
        val urlAccessables = urlAccessables.joinToString { it.toString() }
        val childAccessibilities = childAccessibilities.joinToString { it.toString() }
        return "$id $name $description $urlAccessables $childAccessibilities"
    }
}

data class UrlAccessable(
    @HttpMethod val method: String,
    val uri: String,
)

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY, AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
annotation class HttpMethod {
    companion object {
        const val GET = "GET"
        const val POST = "POST"
        const val PUT = "PUT"
        const val DELETE = "DELETE"
        const val ALL = "ALL"
    }
}
