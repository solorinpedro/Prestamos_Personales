package edu.ucne.prestamos_personales.view

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.prestamos_personales.data.repository.Ocupacionrepository
import edu.ucne.prestamos_personales.data.repository.Personarepository
import edu.ucne.prestamos_personales.model.Persona
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonaViewModel @Inject constructor(
    val personarepository: Personarepository,
    val ocupacionrepository: Ocupacionrepository
):ViewModel(){
    var Nombres by mutableStateOf("")
    var Telefono by mutableStateOf("")
    var Celular by mutableStateOf("")
    var Email by mutableStateOf("")
    var Direccion by mutableStateOf("")
    var FechaNacimiento by mutableStateOf("")
    var ocupacionId by mutableStateOf("")

    var selecteDescripcion by mutableStateOf("")
    var expanded by mutableStateOf(false)

    var listas = ocupacionrepository.List()
        private set

    fun Guardar() {
        viewModelScope.launch {
            personarepository.Insertar(
                Persona(
                    PersonaId = 0,
                    Nombres = Nombres,
                    Telefono = Telefono,
                    Celular = Celular,
                    Email = Email,
                    Direccion = Direccion,
                    FechaNacimiento = FechaNacimiento,
                    ocupacionId = ocupacionId.toInt()
                ))
        }
    }
}
