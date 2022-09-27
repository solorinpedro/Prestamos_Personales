package edu.ucne.prestamos_personales.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Personas")
data class Persona(
    @PrimaryKey(autoGenerate = true)
    val PersonaId: Int,
    val Nombres: String,
    val Telefono: String,
    val Celular: String,
    val Email: String,
    val Direccion: String,
    val FechaNacimiento: String,
    val ocupacionId: Int
)