package edu.ucne.prestamos_personales.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import edu.ucne.prestamos_personales.model.Prestamo
import kotlinx.coroutines.flow.Flow

@Dao
interface PrestamoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  Insertar(prestamo: Prestamo)
    @Query(
        """
    SELECT *
    FROM prestamos
    ORDER BY prestamoId
    """
    )
    fun List(): Flow<List<Prestamo>>
}