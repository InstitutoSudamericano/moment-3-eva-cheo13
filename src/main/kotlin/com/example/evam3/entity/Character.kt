package com.example.evam3.entity

import jakarta.persistence.*

@Entity
@Table(name = "characters")
class Character {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(updatable = false)
    var id: Long? = null

    var name: String? = null
    var role: String? = null
    var description: String? = null
    var cost: Double? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "scene_id", referencedColumnName = "id", insertable = false, updatable = false)
    var sceneId: Scene? = null // Relación ManyToOne con la entidad Scene
}
