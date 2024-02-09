package com.example.evam3.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "characters")
class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    var id: Long? = null

    @NotNull(message = "Name cannot be null")
    @Size(max = 100, message = "Name cannot exceed 100 characters")
    @Column(name = "name", nullable = false, length = 100)
    var name: String? = null

    @NotNull(message = "Role cannot be null")
    @Size(max = 100, message = "Role cannot exceed 100 characters")
    @Column(name = "role", nullable = false, length = 100)
    var role: String? = null

    @Size(max = 255, message = "Description cannot exceed 255 characters")
    @Column(name = "description", length = 255)
    var description: String? = null

    @NotNull(message = "Cost cannot be null")
    @Column(name = "cost", nullable = false, precision = 10, scale = 2)
    var cost: BigDecimal? = null

    @NotNull(message = "Scene ID cannot be null")
    @Column(name = "scene_id", nullable = false)
    var sceneId: Int? = null

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "scene_id", referencedColumnName = "id", insertable = false, updatable = false)
    var scene: Scene? = null
}

