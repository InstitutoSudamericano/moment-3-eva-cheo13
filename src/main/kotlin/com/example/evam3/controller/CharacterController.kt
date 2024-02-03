package com.example.evam3.controller

import com.example.evam3.entity.Character
import com.example.evam3.service.CharacterService
import com.example.evam3.service.SceneService
import org.springframework.beans.factory.annotation.Autowired
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
    fun createCharacter(@RequestBody character: Character): Character {
        return characterService.createCharacter(character)
    }

    @PutMapping("/{id}")
    fun updateCharacter(@PathVariable id: Long, @RequestBody updatedCharacter: Character): Character {
        return characterService.updateCharacter(id, updatedCharacter)
    }

    @PatchMapping("/{id}")
    fun patchCharacter(@PathVariable id: Long, @RequestBody updatedCharacter: Character): Character {
        return characterService.patchCharacter(id, updatedCharacter)
    }

    @DeleteMapping("/delete/{id}")
    fun deleteCharacter(@PathVariable id: Long): Boolean? {
        characterService.deleteCharacter(id)
        return true
    }
}
