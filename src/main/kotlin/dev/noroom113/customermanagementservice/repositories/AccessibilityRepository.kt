package dev.noroom113.customermanagementservice.repositories

import dev.noroom113.customermanagementservice.entities.Accessibility
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface AccessibilityRepository : JpaRepository<Accessibility, Long> {
    fun findAccessibilityByName(name: String): Accessibility?
}
