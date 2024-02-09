package com.example.evam3.service

import com.example.evam3.entity.Character
import com.example.evam3.repository.CharacterRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

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
        return characterRepository.save(character)
    }
    fun putCharacter(updatedCharacter: Character): Character {
        try {
            characterRepository.findById(updatedCharacter.id)
                ?: throw Exception("ID no existe")
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
}