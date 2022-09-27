package edu.ucne.prestamos_personales.data.dao

import androidx.room.*
import edu.ucne.prestamos_personales.model.Ocupacion
import kotlinx.coroutines.flow.Flow

@Dao
interface OcupacionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun Insertar(ocupacion: Ocupacion)

    @Update
    suspend fun Modificar(ocupacion: Ocupacion)

    @Delete
    suspend fun Eliminar(ocupacion: Ocupacion)

    @Query(
        """
        SELECT * 
        FROM ocupaciones
        ORDER BY ocupacionId
    """
    )
    fun List(): Flow<List<Ocupacion>>
    @Query(
        """
        SELECT * 
        FROM ocupaciones
        WHERE ocupacionId =:Id
    """
    )
    fun Buscar(Id:Int): Flow<Ocupacion>
}