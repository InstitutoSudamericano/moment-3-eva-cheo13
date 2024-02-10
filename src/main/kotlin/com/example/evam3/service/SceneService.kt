package com.example.evam3.service

import com.example.evam3.entity.Scene
import com.example.evam3.repository.SceneRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.math.BigInteger

@Service
class SceneService {

    @Autowired
    lateinit var sceneRepository: SceneRepository
    @Autowired
    lateinit var filmService: FilmService
    fun getAllScenes(): List<Scene> {
        return sceneRepository.findAll()
    }

    fun getSceneById(id: Long): Scene {
        return sceneRepository.findById(id)
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Scene not found with id: $id") }
    }

    fun createScene(scene: Scene): Scene {
        validateScene(scene)
        return sceneRepository.save(scene)
    }
    fun putScene(updatedScene: Scene): Scene {
        try {
            sceneRepository.findById(updatedScene.id)
                ?: throw Exception("ID no existe")
            validateScene(updatedScene)
            return sceneRepository.save(updatedScene)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }
    fun updateScene(updatedScene: Scene): Scene {
        try{
            val response = sceneRepository.findById(updatedScene.id)
                ?: throw Exception("ID no existe")
            response.apply {
                title=updatedScene.title
                description=updatedScene.description
            }
            validateScene(response)
            return sceneRepository.save(response)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }



    fun deleteScene(id: Long): Boolean? {
        try{
            val response = sceneRepository.findById(id)
                ?: throw Exception("ID no existe")
            sceneRepository.deleteById(id!!)
            return true
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }

    private fun validateScene(scene: Scene) {
        // Obtén la duración de la película
        val film = filmService.getFilmById(scene.filmId!!.toLong())
        val filmDuration = film.duration

        // Verifica si la duración de la escena es mayor que la duración de la película
        if (scene.minutes!! > filmDuration) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "La duración de la escena no puede ser mayor a la duración de la película")
        }
        val totalDurationOfScenes = sceneRepository.sumDurationByFilmId(scene.filmId) ?: BigInteger.ZERO
        if (totalDurationOfScenes + scene.minutes!! > filmDuration) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "La suma de la duración de las escenas no puede sobrepasar la duración de la película")
        }

    }
}