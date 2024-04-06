package dev.noroom113.customermanagementservice.service

import dev.noroom113.customermanagementservice.repositories.CustomerRepository
import org.springframework.stereotype.Service

@Service
class CustomerService(
    private val customerRepository: CustomerRepository
) {

}
