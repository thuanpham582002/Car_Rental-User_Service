package dev.noroom113.customermanagementservice.entities.dto

import dev.noroom113.customermanagementservice.entities.User
import java.io.Serializable

/**
 * DTO for {@link dev.noroom113.customermanagementservice.entities.User}
 */
data class UserDto(
    val id: Long,
    val name: String,
    val email: String,
    val accessibilityIds: List<Long>,
    val indentityCard: IndentityCardDto,
    val driverLicense: DriverLicenseDto? = null,
) : Serializable