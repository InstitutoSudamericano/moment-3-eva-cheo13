package com.example.evam3.service

import com.example.evam3.entity.Scene
import com.example.evam3.repository.SceneRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
@Service
class SceneService {

    @Autowired
    lateinit var sceneRepository: SceneRepository

    fun getAllScenes(): List<Scene> {
        return sceneRepository.findAll()
    }

    fun getSceneById(id: Long): Scene {
        return sceneRepository.findById(id)
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Scene not found with id: $id") }
    }

    fun createScene(scene: Scene): Scene {
        return sceneRepository.save(scene)
    }

    fun updateScene(id: Long, updatedScene: Scene): Scene {
        if (sceneRepository.existsById(id)) {
            updatedScene.id = id
            return sceneRepository.save(updatedScene)
        } else {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Scene not found with id: $id")
        }
    }

    fun putScene(id: Long, updatedScene: Scene): Scene {
        if (sceneRepository.existsById(id)) {
            updatedScene.id = id
            return sceneRepository.save(updatedScene)
        } else {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Scene not found with id: $id")
        }
    }

    fun deleteScene(id: Long) {
        if (sceneRepository.existsById(id)) {
            sceneRepository.deleteById(id)
        } else {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Scene not found with id: $id")
        }
    }
}