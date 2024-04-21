package dev.noroom113.customermanagementservice.client

import jakarta.persistence.Embeddable
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.ResponseEntity
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
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
    @Scheduled(fixedRate = 5000)
    fun getAccessibilities(){
        val accessibilities = accessibilityServiceClient.getAccessibilities().body
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
    constructor(name: String, description: String) : this(0, name, description)
    constructor(name: String, description: String, urlAccessables: List<UrlAccessable>) : this(
        0,
        name,
        description,
        urlAccessables
    )

    constructor(
        name: String,
        description: String,
        urlAccessables: List<UrlAccessable>,
        childAccessibilities: List<Accessibility>,
    ) : this(0, name, description, urlAccessables, childAccessibilities)

    override fun toString(): String {
        val urlAccessables = urlAccessables.joinToString { it.toString() }
        val childAccessibilities = childAccessibilities.joinToString { it.toString() }
        return "$id $name $description $urlAccessables $childAccessibilities"
    }
}

@Embeddable
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
