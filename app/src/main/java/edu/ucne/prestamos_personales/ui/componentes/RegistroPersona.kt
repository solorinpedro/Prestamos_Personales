package edu.ucne.prestamos_personales.ui.componentes

import android.app.DatePickerDialog
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import edu.ucne.prestamos_personales.view.PersonaViewModel
import java.util.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RegistroPersona(backToDashboard:() -> Unit, viewModel: PersonaViewModel = hiltViewModel()){
    val ScaffoldState = rememberScaffoldState()
    val context = LocalContext.current
    val contexto = LocalContext.current

    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    //Validar
    var NombresValidar by remember { mutableStateOf(false)}
    var TelefonoValidar by remember { mutableStateOf(false)}
    var CelularValidar by remember { mutableStateOf(false)}
    var EmailValidar by remember { mutableStateOf(false)}
    var DireccionValidar by remember { mutableStateOf(false)}

    val date = DatePickerDialog(
        contexto, {d, year, month, day->
            val month = month + 1
            viewModel.FechaNacimiento = "$day / $month / $year"
        },year, month, day
    )

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Registro de persona") })
        },

        scaffoldState = ScaffoldState
    ) {
        it

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {

            OutlinedTextField(
                value = viewModel.Nombres,
                onValueChange = { viewModel.Nombres = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Nombres") },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Person, contentDescription = "Guardar")
                }
            )

            OutlinedTextField(
                value = viewModel.Telefono,
                onValueChange = { viewModel.Telefono = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Telefono") },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Smartphone, contentDescription = "Guardar")
                }
            )

            OutlinedTextField(
                value = viewModel.Celular,
                onValueChange = { viewModel.Celular = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Celular") },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Smartphone, contentDescription = "Guardar")
                }
            )

            OutlinedTextField(
                value = viewModel.Email,
                onValueChange = { viewModel.Email = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Email") },

                leadingIcon = {
                    Icon(imageVector = Icons.Default.Email, contentDescription = "Guardar")
                }

            )

            OutlinedTextField(
                value = viewModel.Direccion,
                onValueChange = { viewModel.Direccion = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Direccion") },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Directions, contentDescription = "Guardar")
                }
            )

            OutlinedTextField(
                value = viewModel.FechaNacimiento,
                onValueChange = { viewModel.FechaNacimiento = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Fecha nacimiento") },
                readOnly = true,

                trailingIcon = {
                    IconButton(
                        onClick = { date.show() }
                    ) {
                        Icon(
                            imageVector = Icons.Default.CalendarToday,
                            contentDescription = "",
                        )
                    }
                }
            )

            ExposedDropdownMenuBox(
                expanded = viewModel.expanded,
                onExpandedChange = {
                    viewModel.expanded = it
                },
            ) {

                TextField(
                    readOnly = true,
                    value = viewModel.selecteDescripcion,
                    onValueChange = { viewModel.selecteDescripcion = it },
                    label = { Text(text = "Ocupaciones") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = viewModel.expanded
                        )
                    },
                    colors = ExposedDropdownMenuDefaults.textFieldColors(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp),
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Work, contentDescription = "Guardar")
                    }
                )

                ExposedDropdownMenu(
                    expanded = viewModel.expanded,
                    onDismissRequest = {
                        viewModel.expanded = false
                    }
                ) {

                    val lista = viewModel.listas.collectAsState(initial = emptyList())

                    lista.value.forEach() { selectocupaciones ->
                        DropdownMenuItem(
                            onClick = {
                                viewModel.ocupacionId = selectocupaciones.ocupacionId.toString()
                                viewModel.selecteDescripcion = selectocupaciones.descripcion
                                viewModel.expanded = false
                            }
                        ) {
                            Text(text = selectocupaciones.descripcion)
                        }
                    }
                }
            }

            Button(
                onClick = {

                    NombresValidar = viewModel.Nombres.isBlank()
                    TelefonoValidar = viewModel.Telefono.isBlank()
                    CelularValidar = viewModel.Celular.isBlank()
                    EmailValidar = viewModel.Email.isBlank()
                    DireccionValidar = viewModel.Telefono.isBlank()


                    if (viewModel.Nombres == "") {
                        Toast.makeText(context, "Nombre no debe estar vácio", Toast.LENGTH_SHORT)
                            .show()
                    }
                    if (viewModel.Telefono.toString() == "") {
                        Toast.makeText(context, "Telefono no debe estar vacio", Toast.LENGTH_SHORT)
                            .show()
                    }
                    if (viewModel.Celular.toString() == "") {
                        Toast.makeText(context, "Celular no debe estar vacio", Toast.LENGTH_SHORT)
                            .show()
                    }
                    if (viewModel.Email.toString() == "") {
                        Toast.makeText(context, "Email no debe estar vacio", Toast.LENGTH_SHORT)
                            .show()
                    }
                    if (viewModel.Direccion.toString() == "") {
                        Toast.makeText(context, "Direccion no debe estar vacio", Toast.LENGTH_SHORT)
                            .show()
                    }

                    if (!NombresValidar && !TelefonoValidar && !CelularValidar && !EmailValidar && !DireccionValidar) {
                        viewModel.Guardar()
                        Toast.makeText(context, "Guardado", Toast.LENGTH_SHORT).show()
                        backToDashboard()
                    }

                },

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
            ) {
                Text(text = "Guardar")
            }
        }
    }
}