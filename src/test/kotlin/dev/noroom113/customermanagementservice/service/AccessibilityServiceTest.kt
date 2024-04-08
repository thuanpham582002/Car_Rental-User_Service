package dev.noroom113.customermanagementservice.service

import dev.noroom113.customermanagementservice.entities.Accessibility
import dev.noroom113.customermanagementservice.exception.BaseException
import dev.noroom113.customermanagementservice.repositories.AccessibilityRepository
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.slf4j.LoggerFactory
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean

@SpringBootTest
class AccessibilityServiceTest {
    private val logger = LoggerFactory.getLogger(AccessibilityServiceTest::class.java)
    @MockBean
    private lateinit var accessibilityRepository: AccessibilityRepository

    private lateinit var accessibilityService: AccessibilityService

    @BeforeEach
    fun setup() {
        MockitoAnnotations.openMocks(this)
        accessibilityService = AccessibilityService(accessibilityRepository)
    }

    @Test
    @DisplayName("Should add accessibility when valid data is provided")
    fun shouldAddAccessibilityWhenValidDataIsProvided() {
        val accessibility = Accessibility(
            name = "Accessibility Name",
            description = "Accessibility Description"
        )

        accessibilityService.addAccessibility(accessibility)
        verify(accessibilityRepository).save(accessibility)
    }

    @Test
    fun `should throw exception when accessibility already exists`() {
        val accessibility = Accessibility(
            name = "Accessibility Name",
            description = "Accessibility Description"
        )
        // name must be unique
        accessibilityService.addAccessibility(accessibility)
        assertThrows(BaseException.AccessibilityAlreadyExistsException::class.java) {
            accessibilityService.addAccessibility(accessibility)
            logger.info(accessibilityService.getAllAccessibilities().size.toString())
        }
    }
}