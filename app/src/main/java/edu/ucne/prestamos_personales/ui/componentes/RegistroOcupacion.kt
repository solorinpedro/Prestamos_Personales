package edu.ucne.prestamos_personales.ui.componentes

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import edu.ucne.prestamos_personales.view.OcupacionViewModel

@Composable
fun RegistroOcupacion (backToDashboard:() -> Unit, viewModel: OcupacionViewModel = hiltViewModel()){
    val ScaffoldState = rememberScaffoldState()
    val context = LocalContext.current
    //validar
    var descripcionValidar by remember { mutableStateOf(false)}
    var salarioValidar by remember { mutableStateOf(false)}
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Registro de ocupaciones") },

                actions =  {

                    IconButton(onClick ={

                    }) {
                        Icon(Icons.Filled.Search, "Buscar")
                    }
                })
        },
        scaffoldState = ScaffoldState
    ) {it

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)) {
            OutlinedTextField(
                value = viewModel.descripcion,
                onValueChange = {viewModel.descripcion = it},
                label = { Text(text = "descripcion") },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Description,
                        contentDescription = null)
                }
            )
            OutlinedTextField(
                value = viewModel.salario,
                onValueChange = {viewModel.salario = it},
                label = { Text(text = "salario") },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Money,
                        contentDescription = null)
                }
            )
            Button(onClick = {
                descripcionValidar = viewModel.descripcion.isBlank()
                salarioValidar = viewModel.salario.isBlank()

                if(viewModel.descripcion.toString() == ""){
                    Toast.makeText(context, "Descrpcion no debe estar vacia", Toast.LENGTH_SHORT).show()
                }
                if(viewModel.salario.toString() == ""){
                    Toast.makeText(context, "Salario no debe estar vacia", Toast.LENGTH_SHORT).show()
                }

                if(!descripcionValidar){
                    if (viewModel.salario.toFloat() > 0){
                        viewModel.Guardar()
                        Toast.makeText(context, "Guardado exitosamente", Toast.LENGTH_SHORT).show()
                        backToDashboard()
                    }else{
                        Toast.makeText(context, "El salario no debe de ser menor a 0", Toast.LENGTH_SHORT).show()
                    }
                }
            },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp)

            ) {
                Text(text = "Guardar")
            }
        }
    }
}
