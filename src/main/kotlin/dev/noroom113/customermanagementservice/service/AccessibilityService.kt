package dev.noroom113.customermanagementservice.service

import dev.noroom113.customermanagementservice.entities.Accessibility
import dev.noroom113.customermanagementservice.exception.BaseException
import dev.noroom113.customermanagementservice.repositories.AccessibilityRepository
import org.springframework.stereotype.Service

@Service
class AccessibilityService(
    private val accessibilityRepository: AccessibilityRepository
) {
    @Throws(BaseException.AccessibilityAlreadyExistsException::class)
    fun addAccessibility(accessibility: Accessibility) {
        if (accessibilityRepository.findAccessibilityByName(accessibility.name) != null) {
            throw BaseException.AccessibilityAlreadyExistsException()
        }
        accessibilityRepository.save(accessibility)
    }

    fun getAllAccessibilities(): List<Accessibility> {
        return accessibilityRepository.findAll()
    }
}
