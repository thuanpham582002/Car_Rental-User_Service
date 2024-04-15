package dev.noroom113.customermanagementservice.service

import dev.noroom113.customermanagementservice.repositories.IndetityCardRepository
import org.springframework.stereotype.Service

@Service
class IndetityCardService(
    private val indetityCardRepository: IndetityCardRepository
) {
}
