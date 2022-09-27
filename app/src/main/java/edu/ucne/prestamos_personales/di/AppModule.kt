package edu.ucne.prestamos_personales.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import edu.ucne.prestamos_personales.data.PrestamosDb
import edu.ucne.prestamos_personales.data.dao.OcupacionDao
import edu.ucne.prestamos_personales.data.dao.PersonaDao
import edu.ucne.prestamos_personales.data.dao.PrestamoDao
import edu.ucne.prestamos_personales.data.repository.Ocupacionrepository
import edu.ucne.prestamos_personales.data.repository.Personarepository
import edu.ucne.prestamos_personales.data.repository.Prestamorepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun ProvideTicketDb(@ApplicationContext context: Context): PrestamosDb {
        val DATABASE_NAME = "PrestamoDb"
        return Room.databaseBuilder(
            context,
            PrestamosDb::class.java,
            DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()

    }
    @Provides
    fun ProvidePrestamoDAO(prestamosDb: PrestamosDb): OcupacionDao {
        return prestamosDb.OcupacionDao
    }

    @Provides
    fun ProvidePrestamoRepository(prestamosDao: OcupacionDao): Ocupacionrepository {
        return Ocupacionrepository(prestamosDao)
    }

    @Provides
    fun ProvidesPrestamosDAO(prestamosDb: PrestamosDb): PersonaDao {
        return prestamosDb.PersonaDao
    }
    @Provides
    fun ProvidesPrestamorePepository(prestamosDao: PersonaDao): Personarepository {
        return Personarepository(prestamosDao)
    }
    @Provides
    fun ProvidePrestamosDAO(prestamosDb: PrestamosDb): PrestamoDao {
        return  prestamosDb.PrestamoDao
    }
    @Provides
    fun ProvidesPrestamoreRepository(prestamosDao: PrestamoDao):Prestamorepository{
        return Prestamorepository(prestamosDao)
    }

}