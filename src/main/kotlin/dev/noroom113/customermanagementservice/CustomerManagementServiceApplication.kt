package dev.noroom113.customermanagementservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class CustomerManagementServiceApplication

fun main(args: Array<String>) {
	runApplication<CustomerManagementServiceApplication>(*args)
}
