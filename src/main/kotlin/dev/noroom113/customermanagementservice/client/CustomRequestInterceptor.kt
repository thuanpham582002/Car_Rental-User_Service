package dev.noroom113.customermanagementservice.client

import feign.RequestInterceptor
import feign.RequestTemplate
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes


@Component
class CustomRequestInterceptor : RequestInterceptor {
    override fun apply(template: RequestTemplate) {
        if (RequestContextHolder.getRequestAttributes() != null && RequestContextHolder.getRequestAttributes() is ServletRequestAttributes) {
            val request =
                (RequestContextHolder.getRequestAttributes() as ServletRequestAttributes?)!!.request
            val authorization = request.getHeader("Authorization")
            if (authorization != null) {
                template.header("Authorization", (authorization))
            }
        }
    }
}