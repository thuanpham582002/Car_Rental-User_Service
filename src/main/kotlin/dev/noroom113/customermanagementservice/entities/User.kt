package dev.noroom113.customermanagementservice.entities

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany

@Entity(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,
    val firstName: String,
    val lastName: String,
    val email: String,
    val userName: String,
    val password: String,
    @OneToMany
    val accessibilitys: List<Accessibility>
){
    constructor(
        firstName: String,
        lastName: String,
        email: String,
        userName: String,
        password: String,
        accessibilitys: List<Accessibility>
    ) : this(0, firstName, lastName, email, userName, password, accessibilitys)
}
    