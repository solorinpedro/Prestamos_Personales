package edu.ucne.prestamos_personales.data.repository

import edu.ucne.prestamos_personales.data.dao.OcupacionDao
import edu.ucne.prestamos_personales.model.Ocupacion
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Ocupacionrepository @Inject constructor(

    val ocupacionDao: OcupacionDao

) {
    suspend fun Insertar(ocupacion: Ocupacion) = ocupacionDao.Insertar(ocupacion)

    suspend fun Modificar(ocupacion: Ocupacion)= ocupacionDao.Modificar(ocupacion)

    suspend fun Eliminar(ocupacion: Ocupacion)= ocupacionDao.Eliminar(ocupacion)

    suspend fun Buscar(ocupacion:Int)= ocupacionDao.Buscar(ocupacion)

    fun List(): Flow<List<Ocupacion>> = ocupacionDao.List()
}