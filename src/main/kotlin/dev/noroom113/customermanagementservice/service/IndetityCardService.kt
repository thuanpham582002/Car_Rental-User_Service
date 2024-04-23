package dev.noroom113.customermanagementservice.service

import dev.noroom113.customermanagementservice.entities.IndentityCard
import dev.noroom113.customermanagementservice.repositories.IndetityCardRepository
import org.springframework.stereotype.Service

@Service
class IndetityCardService(
    private val indetityCardRepository: IndetityCardRepository
) {
    fun findByIdentityNumber(identityId: String): IndentityCard? {
        return indetityCardRepository.findIndentityCardByNumber(identityId)
    }
}
