package edu.ucne.prestamos_personales.data.repository

import edu.ucne.prestamos_personales.data.dao.PrestamoDao
import edu.ucne.prestamos_personales.model.Prestamo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Prestamorepository @Inject constructor(
    val prestamoDao: PrestamoDao
){
    suspend fun  Insertar(prestamos: Prestamo)=prestamoDao.Insertar(prestamos)

    fun List(): Flow<List<Prestamo>> = prestamoDao.List()

}