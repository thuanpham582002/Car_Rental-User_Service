package dev.noroom113.customermanagementservice.repositories

import dev.noroom113.customermanagementservice.entities.IndentityCard
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
abstract class IndetityCardRepository : JpaRepository<IndentityCard, Long> {

}
