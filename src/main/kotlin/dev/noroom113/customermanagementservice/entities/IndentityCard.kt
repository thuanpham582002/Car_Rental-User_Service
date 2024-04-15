package dev.noroom113.customermanagementservice.entities

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.sql.Date

@Entity
data class IndentityCard(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long,
    val number: String,
    val imageBase64: String,
    val created_at: Date,
    val updated_at: Date,
){
    constructor(
        number: String,
        imageBase64: String,
        created_at: Date,
        updated_at: Date,
    ) : this(0, number, imageBase64, created_at, updated_at)
}
