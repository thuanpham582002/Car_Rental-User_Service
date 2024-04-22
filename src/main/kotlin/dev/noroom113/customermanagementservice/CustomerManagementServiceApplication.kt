package dev.noroom113.customermanagementservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
@EnableFeignClients
@EnableScheduling
class CustomerManagementServiceApplication

fun main(args: Array<String>) {
	runApplication<CustomerManagementServiceApplication>(*args)
}
