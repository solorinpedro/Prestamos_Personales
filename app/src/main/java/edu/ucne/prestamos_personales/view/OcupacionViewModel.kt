package edu.ucne.prestamos_personales.view

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.prestamos_personales.data.repository.Ocupacionrepository
import edu.ucne.prestamos_personales.model.Ocupacion
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OcupacionViewModel @Inject constructor(
    val ocupacionrepository: Ocupacionrepository

):ViewModel(){
    var descripcion by mutableStateOf("")
    var salario by mutableStateOf("")

    var prestamo = ocupacionrepository.List()
        private set

    fun Guardar(){
        viewModelScope.launch {
            ocupacionrepository.Insertar(
                Ocupacion(
                    ocupacionId = 0,
                    descripcion = descripcion,
                    salario = salario.toFloat()
                )
            )
        }
    }
    fun Buscar() {
        viewModelScope.launch {

        }
    }
}
