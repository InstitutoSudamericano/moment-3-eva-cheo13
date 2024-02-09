package com.example.evam3.controller

import com.example.evam3.entity.Film
import com.example.evam3.service.FilmService
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/film")
class FilmController {

    @Autowired
    lateinit var filmService: FilmService
    @GetMapping
    fun getAllFilms(): List<Film> {
        return filmService.getAllFilms()
    }
    @GetMapping("/{id}")
    fun getFilmById(@PathVariable("id") id: Long): Film {
        return filmService.getFilmById(id)
    }
    @PostMapping
    fun createFilm(@RequestBody @Valid film: Film): ResponseEntity<*> {
        val createdFilm = filmService.createFilm(film)
        return ResponseEntity(createdFilm, HttpStatus.CREATED)
    }
    @PutMapping("/{id}")
    fun putFilm(@RequestBody updatedFilm: Film): ResponseEntity<Film>{
        return ResponseEntity(filmService.putFilm(updatedFilm), HttpStatus.OK)
    }

    @PatchMapping("/{id}")
    fun updateFilm(@PathVariable id: Long, @RequestBody updatedFilm: Film): ResponseEntity<Film> {
        return ResponseEntity(filmService.updateFilm(updatedFilm), HttpStatus.OK)
    }

    @DeleteMapping("/delete/{id}")
    fun deleteFilm(@PathVariable("id") id: Long): Boolean? {
        filmService.deleteFilm(id)
        return true
    }
}