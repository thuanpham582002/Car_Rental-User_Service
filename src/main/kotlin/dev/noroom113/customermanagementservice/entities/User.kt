package dev.noroom113.customermanagementservice.entities

import jakarta.persistence.*
import java.sql.Date

@Entity(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,
    val name: String,
    val email: String,
    val userName: String,
    val password: String,
    @OneToMany
    val accessibilitys: List<Accessibility>,
    @OneToOne
    val indentityCard: IndentityCard,
    @OneToOne
    val driverLicense: DriverLicense?,
    val created_at: Date,
    val updated_at: Date,
){
    constructor(
        name: String,
        email: String,
        userName: String,
        password: String,
        accessibilitys: List<Accessibility>,
        indentityCard: IndentityCard,
        driverLicense: DriverLicense?,
        created_at: Date,
        updated_at: Date,
    ) : this(0, name, email, userName, password, accessibilitys, indentityCard, driverLicense, created_at, updated_at)
}
    