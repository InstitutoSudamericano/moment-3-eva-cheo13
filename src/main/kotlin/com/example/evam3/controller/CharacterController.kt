package com.example.evam3.controller

import com.example.evam3.entity.Character
import com.example.evam3.service.CharacterService
import com.example.evam3.service.SceneService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
@RestController
@RequestMapping("/characters")
class CharacterController {

    @Autowired
    lateinit var characterService: CharacterService
    @Autowired
    lateinit var sceneService: SceneService
    @GetMapping
    fun getAllCharacters(): List<Character> {
        return characterService.getAllCharacters()
    }

    @GetMapping("/{id}")
    fun getCharacterById(@PathVariable id: Long): Character {
        return characterService.getCharacterById(id)
    }

    @PostMapping
    fun createCharacter(@RequestBody character: Character): ResponseEntity<Character> {
        val createCharacter = characterService.createCharacter(character)
        return ResponseEntity(createCharacter, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun putCharacter(@PathVariable id: Long, @RequestBody updatedCharacter: Character): Character {
        return characterService.putCharacter(updatedCharacter)
    }

    @PatchMapping("/{id}")
    fun updateCharacter(@PathVariable id: Long, @RequestBody updatedCharacter: Character): Character {
        return characterService.updateCharacter(updatedCharacter)
    }

    @DeleteMapping("/delete/{id}")
    fun deleteCharacter(@PathVariable id: Long): Boolean? {
        characterService.deleteCharacter(id)
        return true
    }
}
