package dev.noroom113.customermanagementservice.repositories

import dev.noroom113.customermanagementservice.entities.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {
     fun findCustomerByUserName(username: String): User?
}
