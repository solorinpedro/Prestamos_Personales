package edu.ucne.prestamos_personales.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import edu.ucne.prestamos_personales.model.Persona
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun Insertar(persona: Persona)

    @Query(
        """
    SELECT *
    FROM personas
    ORDER BY personaId
    """
    )
    fun List(): Flow<List<Persona>>
}