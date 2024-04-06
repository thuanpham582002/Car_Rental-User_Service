package dev.noroom113.customermanagementservice.config.init

import dev.noroom113.customermanagementservice.controller.AccessibilityController
import dev.noroom113.customermanagementservice.entities.Accessibility
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class AccessibilityStartupRunner(
    private val accessibilityController: AccessibilityController
) : CommandLineRunner {
    override fun run(vararg args: kotlin.String?) {
        accessibilityController.addAccessibility(Accessibility("Staff", "This accessibility is for staff"))
        accessibilityController.addAccessibility(Accessibility("Customer", "This accessibility is for customers"))
    }
}