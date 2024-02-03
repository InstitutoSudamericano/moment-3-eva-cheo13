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
    @Autowired
    lateinit var filmService: FilmService

    @GetMapping
    fun getAllScenes(): List<Scene> {
        return sceneService.getAllScenes()
    }

    @GetMapping("/{id}")
    fun getSceneById(@PathVariable id: Long): ResponseEntity<Scene> {
        val scene = sceneService.getSceneById(id)
        return ResponseEntity.ok(scene)
    }

    @PostMapping
    fun createScene(@RequestBody scene: Scene): ResponseEntity<Scene> {
        val createdScene = sceneService.createScene(scene)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdScene)
    }

    @PutMapping("/{id}")
    fun updateScene(@PathVariable id: Long, @RequestBody updatedScene: Scene): ResponseEntity<Scene> {
        val scene = sceneService.updateScene(id, updatedScene)
        return ResponseEntity.ok(scene)
    }

    @PatchMapping("/{id}")
    fun patchScene(@PathVariable id: Long, @RequestBody updatedScene: Scene): ResponseEntity<Scene> {
        val scene = sceneService.updateScene(id, updatedScene)
        return ResponseEntity.ok(scene)
    }

    @DeleteMapping("/delete/{id}")
    fun deleteScene(@PathVariable id: Long): Boolean? {
        sceneService.deleteScene(id)
        return true
    }
}