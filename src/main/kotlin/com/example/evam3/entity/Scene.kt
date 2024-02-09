package com.example.evam3.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import jakarta.persistence.*
import java.math.BigDecimal
import java.math.BigInteger

@Entity
@Table(name = "scene")
class Scene {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    var id: Long? = null

    @NotNull(message = "Title cannot be null")
    @Size(max = 100, message = "Title cannot exceed 100 characters")
    @Column(name = "title", nullable = false, length = 100)
    var title: String? = null

    @NotNull(message = "Description cannot be null")
    @Size(max = 255, message = "Description cannot exceed 255 characters")
    @Column(name = "description", length = 255)
    var description: String? = null

    @NotNull(message = "Budget cannot be null")
    @Column(name = "budget", nullable = false, precision = 10, scale = 2)
    var budget: BigDecimal? = null

    @NotNull(message = "Minutes cannot be null")
    @Column(name = "minutes", nullable = false)
    var minutes: BigInteger? = null

    @NotNull(message = "Film ID cannot be null")
    @Column(name = "film_id", nullable = false)
    var filmId: Int? = null

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "film_id", referencedColumnName = "id", insertable = false, updatable = false)
    var film: Film? = null
}
