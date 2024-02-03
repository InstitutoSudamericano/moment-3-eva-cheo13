package com.example.evam3.controller

import com.example.evam3.entity.Film
import com.example.evam3.service.FilmService
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
    fun getFilmById(@PathVariable id: Long): Film {
        return filmService.getFilmById(id)
    }
    @PostMapping
    fun createFilm(@RequestBody film: Film): ResponseEntity<Film> {
        val createdFilm = filmService.createFilm(film)
        return ResponseEntity(createdFilm, HttpStatus.CREATED)
    }
    @PostMapping("/{id}")
    fun updateFilm(@PathVariable id: Long, @RequestBody updatedFilm: Film): Film {
        return filmService.updateFilm(id, updatedFilm)
    }
    @PutMapping("/{id}")
    fun putFilm(@PathVariable id: Long, @RequestBody updatedFilm: Film): Film {
        return filmService.putFilm(id, updatedFilm)
    }
    @DeleteMapping("/delete/{id}")
    fun deleteFilm(@PathVariable id: Long): Boolean? {
        filmService.deleteFilm(id)
        return true
    }
}