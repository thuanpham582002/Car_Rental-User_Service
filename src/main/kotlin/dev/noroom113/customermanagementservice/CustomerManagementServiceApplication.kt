package dev.noroom113.customermanagementservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CustomerManagementServiceApplication

fun main(args: Array<String>) {
	runApplication<CustomerManagementServiceApplication>(*args)
}
