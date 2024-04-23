package dev.noroom113.customermanagementservice.entities

import dev.noroom113.customermanagementservice.entities.dto.DriverLicenseDto
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.sql.Date

@Entity
data class DriverLicense(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,
    val number: String,
    val created_at: Date,
    val updated_at: Date,
){
    constructor(number: String) : this(0, number, Date(System.currentTimeMillis()), Date(System.currentTimeMillis()))
}

fun DriverLicense.toDriverLicenseDto(): DriverLicenseDto {
    return DriverLicenseDto(
        id = id,
        number = number,
    )
}