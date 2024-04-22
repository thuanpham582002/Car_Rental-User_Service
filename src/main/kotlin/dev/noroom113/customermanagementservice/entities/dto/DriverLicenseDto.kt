package dev.noroom113.customermanagementservice.entities.dto

import java.io.Serializable
import java.sql.Date

/**
 * DTO for {@link dev.noroom113.customermanagementservice.entities.DriverLicense}
 */
data class DriverLicenseDto(
    val id: Long,
    val number: String,
    val imageBase64: String? = null,
) : Serializable