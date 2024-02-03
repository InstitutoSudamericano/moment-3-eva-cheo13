package com.example.evam3.entity

import jakarta.persistence.*
import java.util.Date

@Entity
@Table(name = "film")
class Film {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(updatable = false)
    var id: Long? = null

    var title: String? = null
    var director: String? = null
    var duration: Double? = null // Cambiado a Double para manejar el tipo DECIMAL
    var releaseDate: Date? = null // Usando Date para release_date
    var language: String? = null
    var genre: String? = null
    var image_url: String? = null
}
