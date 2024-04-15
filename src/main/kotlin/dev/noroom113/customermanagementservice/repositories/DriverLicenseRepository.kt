package dev.noroom113.customermanagementservice.repositories

import dev.noroom113.customermanagementservice.entities.DriverLicense
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DriverLicenseRepository : JpaRepository<DriverLicense, Long> {

}
