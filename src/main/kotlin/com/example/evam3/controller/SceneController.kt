package com.example.evam3.controller

import com.example.evam3.entity.Scene
import com.example.evam3.service.FilmService
import com.example.evam3.service.SceneService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
@RestController
@RequestMapping("/scene")
class SceneController {

    @Autowired
    lateinit var sceneService: SceneService

    @GetMapping
    fun getAllScenes(): List<Scene> {
        return sceneService.getAllScenes()
    }

    @GetMapping("/{id}")
    fun getSceneById(@PathVariable id: Long): Scene {
        return sceneService.getSceneById(id)
    }

    @PostMapping
    fun createScene(@RequestBody scene: Scene): ResponseEntity<Scene> {
        val createdScene = sceneService.createScene(scene)
        return ResponseEntity(createdScene, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun putScene(@PathVariable id: Long, @RequestBody updatedScene: Scene): Scene{
        return sceneService.putScene(updatedScene)
    }

    @PatchMapping("/{id}")
    fun updateScene(@PathVariable id: Long, @RequestBody updatedScene: Scene): Scene {
        return sceneService.updateScene(updatedScene)
    }

    @DeleteMapping("/delete/{id}")
    fun deleteScene(@PathVariable id: Long): Boolean? {
        sceneService.deleteScene(id)
        return true
    }
}