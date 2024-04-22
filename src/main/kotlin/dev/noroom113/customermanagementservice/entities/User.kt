package dev.noroom113.customermanagementservice.entities

import dev.noroom113.customermanagementservice.entities.dto.UserDto
import jakarta.persistence.*
import java.sql.Date

@Entity(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,
    val name: String,
    val email: String,
    val password: String,
    @ElementCollection
    val accessibilityIds: List<Long>,
    @OneToOne(cascade = [CascadeType.ALL])
    val indentityCard: IndentityCard,
    @OneToOne(cascade = [CascadeType.ALL])
    val driverLicense: DriverLicense?,
    val created_at: Date,
    val updated_at: Date,
){
    constructor(
        email: String,
        userName: String,
        password: String,
        accessibilityIds: List<Long>,
        indentityCard: IndentityCard,
        driverLicense: DriverLicense?,
        created_at: Date,
        updated_at: Date,
    ) : this(0, email, userName, password, accessibilityIds, indentityCard, driverLicense, created_at, updated_at)

    constructor(identityId: String, username: String, email: String, password: String) : this(
        0,
        email,
        username,
        password,
        emptyList(),
        IndentityCard(identityId),
        null,
        Date(System.currentTimeMillis()),
        Date(System.currentTimeMillis())
    )

    fun toUserDto(): UserDto {
        return UserDto(
            id = id,
            name = name,
            email = email,
            accessibilityIds = accessibilityIds,
            indentityCard = indentityCard.toIndentityCardDto(),
            driverLicense = driverLicense?.toDriverLicenseDto()
        )
    }
}
