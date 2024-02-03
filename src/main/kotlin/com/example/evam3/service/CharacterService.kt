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

    fun updateCharacter(id: Long, updatedCharacter: Character): Character {
        if (characterRepository.existsById(id)) {
            updatedCharacter.id = id
            return characterRepository.save(updatedCharacter)
        } else {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Character not found with id: $id")
        }
    }

    fun patchCharacter(id: Long, updatedCharacter: Character): Character {
        if (characterRepository.existsById(id)) {
            updatedCharacter.id = id
            return characterRepository.save(updatedCharacter)
        } else {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Character not found with id: $id")
        }
    }

    fun deleteCharacter(id: Long) {
        if (characterRepository.existsById(id)) {
            characterRepository.deleteById(id)
        } else {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Character not found with id: $id")
        }
    }
}