package com.example.evam3.service

import com.example.evam3.entity.Character
import com.example.evam3.repository.CharacterRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.math.BigDecimal

@Service
class CharacterService {

    @Autowired
    lateinit var characterRepository: CharacterRepository
    @Autowired
    lateinit var sceneService: SceneService
    fun getAllCharacters(): List<Character> {
        return characterRepository.findAll()
    }

    fun getCharacterById(id: Long): Character {
        return characterRepository.findById(id)
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Character not found with id: $id") }
    }
    fun createCharacter(character: Character): Character {
        validateCharacter(character)
        return characterRepository.save(character)
    }
    fun putCharacter(updatedCharacter: Character): Character {
        try {
            characterRepository.findById(updatedCharacter.id)
                ?: throw Exception("ID no existe")
            validateCharacter(updatedCharacter)
            return characterRepository.save(updatedCharacter)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }

    fun updateCharacter(updatedCharacter: Character): Character {
        try{
            val response = characterRepository.findById(updatedCharacter.id)
                ?: throw Exception("ID no existe")
            response.apply {
                name=updatedCharacter.name
                description=updatedCharacter.description
            }
            validateCharacter(response)
            return characterRepository.save(response)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }

    fun deleteCharacter(id: Long): Boolean? {
        try{
            val response = characterRepository.findById(id)
                ?: throw Exception("ID no existe")
            characterRepository.deleteById(id!!)
            return true
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }

    private fun validateCharacter(character: Character) {
        // Obtén la escena asociada al personaje
        val sceneId = character.sceneId ?: throw ResponseStatusException(HttpStatus.BAD_REQUEST, "El personaje debe estar asociado a una escena")
        val scene = sceneService.getSceneById(sceneId.toLong())

        // Verifica si el costo del personaje es mayor que el presupuesto de la escena
        if (character.cost!! > scene.budget) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "El costo del personaje no puede ser mayor al presupuesto de la escena")
        }

        // Verifica si la suma de los costos de los personajes en la escena es válida
        val totalCostOfCharacters = characterRepository.sumCostBySceneId(scene.id) ?: BigDecimal.ZERO
        if (totalCostOfCharacters + character.cost!! > scene.budget) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "La suma de los costos de los personajes no puede sobrepasar el presupuesto de la escena")
        }


    }

}


