package edu.ucne.prestamos_personales.data.repository

import edu.ucne.prestamos_personales.data.dao.PersonaDao
import edu.ucne.prestamos_personales.model.Persona
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Personarepository @Inject constructor(
    val PersonaDao: PersonaDao

){
    suspend fun Insertar (persona: Persona) = PersonaDao.Insertar(persona)

    fun List(): Flow<List<Persona>> = PersonaDao.List()

}