package edu.ucne.prestamos_personales.data

import androidx.room.Database
import androidx.room.RoomDatabase
import edu.ucne.prestamos_personales.data.dao.OcupacionDao
import edu.ucne.prestamos_personales.data.dao.PersonaDao
import edu.ucne.prestamos_personales.data.dao.PrestamoDao
import edu.ucne.prestamos_personales.model.Ocupacion
import edu.ucne.prestamos_personales.model.Persona
import edu.ucne.prestamos_personales.model.Prestamo


@Database(
    entities = [Ocupacion::class, Persona::class, Prestamo::class],
    version = 3
)
abstract class PrestamosDb:RoomDatabase() {
    abstract val OcupacionDao: OcupacionDao

    abstract val PersonaDao: PersonaDao

    abstract  val PrestamoDao: PrestamoDao
}