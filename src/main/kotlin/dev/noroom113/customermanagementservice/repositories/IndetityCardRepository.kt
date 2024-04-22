package dev.noroom113.customermanagementservice.repositories

import dev.noroom113.customermanagementservice.entities.IndentityCard
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface IndetityCardRepository : JpaRepository<IndentityCard, Long> {
    fun findIndentityCardByNumber(identityId: String): IndentityCard?

}
