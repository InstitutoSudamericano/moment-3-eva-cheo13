package com.example.evam3.service

import com.example.evam3.entity.Film
import com.example.evam3.repository.FilmRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
@Service
class FilmService {

    @Autowired
    lateinit var filmRepository: FilmRepository
    fun getAllFilms(): List<Film> {
        return filmRepository.findAll()
    }

    fun getFilmById(id: Long): Film {
        return filmRepository.findById(id)
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Film not found with id: $id") }
    }

    fun createFilm(film: Film): Film {
        return filmRepository.save(film)
    }
    fun updateFilm(id: Long, updatedFilm: Film): Film {
        if (filmRepository.existsById(id)) {
            updatedFilm.id = id
            return filmRepository.save(updatedFilm)
        } else {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Film not found with id: $id")
        }
    }

    fun putFilm(id: Long, updatedFilm: Film): Film {
        if (filmRepository.existsById(id)) {
            updatedFilm.id = id
            return filmRepository.save(updatedFilm)
        } else {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Film not found with id: $id")
        }
    }

    fun deleteFilm(id: Long) {
        if (filmRepository.existsById(id)) {
            filmRepository.deleteById(id)
        } else {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Film not found with id: $id")
        }
    }
}