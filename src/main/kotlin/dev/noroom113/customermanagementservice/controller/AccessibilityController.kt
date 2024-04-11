package dev.noroom113.customermanagementservice.controller

import dev.noroom113.customermanagementservice.entities.Accessibility
import dev.noroom113.customermanagementservice.service.AccessibilityService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/accessibility")
class AccessibilityController(
    private val accessibilityService: AccessibilityService
) {
    @GetMapping("/add")
    fun addAccessibility(@RequestBody accessibility: Accessibility) {
        accessibilityService.addAccessibility(accessibility)
    }

    @GetMapping("/all")
    fun getAllAccessibilities(): List<Accessibility> {
        return accessibilityService.getAllAccessibilities()
    }
}