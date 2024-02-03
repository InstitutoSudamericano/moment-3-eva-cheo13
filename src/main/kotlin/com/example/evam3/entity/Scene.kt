package com.example.evam3.entity

import jakarta.persistence.*

@Entity
@Table(name = "scene")
class Scene {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(updatable = false)
    var id: Long? = null

    var title: String? = null
    var description: String? = null
    var budget: Double? = null
    var minutes: Int? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "film_id", referencedColumnName = "id", insertable = false, updatable = false)
    var filmId: Film? = null // Relación ManyToOne con la entidad Film

   }
