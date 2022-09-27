package edu.ucne.prestamos_personales.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Ocupaciones")
data class Ocupacion (
    @PrimaryKey(autoGenerate = true)
    val ocupacionId: Int,
    val descripcion: String,
    val salario: Float
)