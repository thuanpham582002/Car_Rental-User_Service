package dev.noroom113.customermanagementservice.entities

import dev.noroom113.customermanagementservice.entities.dto.IndentityCardDto
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.UniqueConstraint
import java.sql.Date

@Entity
data class IndentityCard(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,
    @Column(unique = true)
    val number: String,
    val created_at: Date = Date(System.currentTimeMillis()),
    val updated_at: Date = Date(System.currentTimeMillis()),
) {
    constructor(
        number: String,
        created_at: Date = Date(System.currentTimeMillis()),
        updated_at: Date = Date(System.currentTimeMillis()),
    ) : this(0, number, created_at, updated_at)
}

fun IndentityCard.toIndentityCardDto(): IndentityCardDto {
    return IndentityCardDto(
        id = id,
        number = number,
    )
}
