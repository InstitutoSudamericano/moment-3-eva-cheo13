package com.example.evam3.entity

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import jakarta.persistence.*
import java.math.BigInteger
import java.util.Date

@Entity
@Table(name = "film")
class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id", updatable = false)
    var id: Long? = null

    @NotNull(message = "Title cannot be null")
    @Size(max = 100, message = "Title cannot exceed 100 characters")
    @Column(name = "title", nullable = false, length = 100)
    var title: String? = null

    @NotNull(message = "Director cannot be null")
    @Size(max = 100, message = "Director cannot exceed 100 characters")
    @Column(name = "director", length = 100)
    var director: String? = null

    @NotNull(message = "Duration cannot be null")
    @Column(name = "duration", nullable = false)
    var duration: Int? = null

    @NotNull(message = "Date not null")
    @Column(name = "release_date")
    var releaseDate: Date? = null

    @NotNull(message = "Languages not null")
    @Size(max = 50, message = "Languages cannot exceed 50 characters")
    @Column(name = "languages", length = 50)
    var languages: String? = null

    @NotNull(message = "Genre not null")
    @Size(max = 50, message = "Genre cannot exceed 50 characters")
    @Column(name = "genre", length = 50)
    var genre: String? = null

    @NotNull(message = "IMG not null")
    @Size(max = 255, message = "Image URL cannot exceed 255 characters")
    @Column(name = "image_url", length = 255)
    var imageUrl: String? = null
}
