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
    fun putFilm(updatedFilm: Film): Film {
        try {
            filmRepository.findById(updatedFilm.id)
                ?: throw Exception("ID no existe")
            return filmRepository.save(updatedFilm)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }




    fun updateFilm(updatedFilm: Film): Film {
        try{
            val response = filmRepository.findById(updatedFilm.id)
                ?: throw Exception("ID no existe")
            response.apply {
                title=updatedFilm.title
                director=updatedFilm.director
            }
            return filmRepository.save(response)
        }
        catch (ex:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,ex.message)
        }
    }



    fun deleteFilm(id: Long) {
        val existingFilm = filmRepository.findById(id)
            .orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND, "Invoice no encontrado") }

        return filmRepository.delete(existingFilm)
    }

    private fun validateFilm(film: Film) {

        //if (film.director.isNullOrBlank() || film.genre.isNullOrBlank() || film.languages.isNullOrBlank()) {
            // throw ValidationException("Campos obligatorios no pueden estar vacíos")
        //}
    }
}