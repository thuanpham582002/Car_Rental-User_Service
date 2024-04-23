package dev.noroom113.customermanagementservice.entities

import java.io.Serializable

data class UrlAccessable(
    val uri: String,
    val method: Set<HttpMethod> = setOf(HttpMethod.ALL),
) : Serializable

enum class HttpMethod {
    GET, POST, PUT, DELETE, ALL
}
