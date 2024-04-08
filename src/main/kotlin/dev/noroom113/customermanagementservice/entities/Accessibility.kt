package dev.noroom113.customermanagementservice.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.UniqueConstraint

@Entity
data class Accessibility(
    @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long,
    @Column(unique = true)
    val name: String,
    val description: String
){
    constructor(name: String, description: String) : this(0, name, description)

    override fun toString(): String {
        return name
    }
}
