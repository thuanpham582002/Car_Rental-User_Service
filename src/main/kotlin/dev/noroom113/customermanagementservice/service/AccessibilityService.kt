package dev.noroom113.customermanagementservice.service

import dev.noroom113.customermanagementservice.entities.Accessibility
import dev.noroom113.customermanagementservice.repositories.AccessibilityRepository
import org.springframework.stereotype.Service

@Service
class AccessibilityService(
    private val accessibilityRepository: AccessibilityRepository
) {
    fun addAccessibility(accessibility: Accessibility) {
        accessibilityRepository.save(accessibility)
    }

}
