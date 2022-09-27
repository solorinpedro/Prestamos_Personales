package edu.ucne.prestamos_personales.ui.componentes

import android.app.DatePickerDialog
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.Work
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import edu.ucne.prestamos_personales.view.PrestamoViewModel
import java.util.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RegistroPrestamo(backToDashboard:() -> Unit, viewModel: PrestamoViewModel = hiltViewModel()) {
    val ScaffoldState = rememberScaffoldState()
    val context = LocalContext.current
    val contexto = LocalContext.current

    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    //Validar
    var MontoValidar by remember { mutableStateOf(false) }
    var ConceptoValidar by remember { mutableStateOf(false) }
    var BalanceValidar by remember { mutableStateOf(false) }

    val date = DatePickerDialog(
        contexto, { d, year, month, day ->
            val month = month + 1
            viewModel.Fecha = "$day / $month / $year"
        }, year, month, day
    )
    val vence = DatePickerDialog(
        contexto, { d, year, month, day ->
            val month = month + 1
            viewModel.Vence = "$day / $month / $year"
        }, year, month, day
    )
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Registro de prestamo") })
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
                value = viewModel.Monto,
                onValueChange = { viewModel.Monto = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Monto") },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Money, contentDescription = "Guardar")
                }
            )
            OutlinedTextField(
                value = viewModel.Balance,
                onValueChange = { viewModel.Balance = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Balance") },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Money, contentDescription = "Guardar")
                }
            )

            OutlinedTextField(
                value = viewModel.Concepto,
                onValueChange = { viewModel.Concepto = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Concepto") },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Description, contentDescription = "Guardar")
                }
            )
            OutlinedTextField(
                value = viewModel.Fecha,
                onValueChange = { viewModel.Fecha = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Fecha") },
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
            OutlinedTextField(
                value = viewModel.Vence,
                onValueChange = { viewModel.Vence = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Vence") },
                readOnly = true,

                trailingIcon = {
                    IconButton(
                        onClick = { vence.show() }
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
                    value = viewModel.selectePersona,
                    onValueChange = { viewModel.selectePersona = it },
                    label = { Text(text = "Personas") },
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

                    lista.value.forEach() { selectedpersona ->
                        DropdownMenuItem(
                            onClick = {
                                viewModel.personaId = selectedpersona.PersonaId.toString()
                                viewModel.selectePersona = selectedpersona.Nombres
                                viewModel.expanded = false

                            }
                        ) {
                            Text(text = selectedpersona.Nombres)
                        }
                    }
                }
            }

            Button(
                onClick = {

                    BalanceValidar = viewModel.Balance.isBlank()
                    ConceptoValidar = viewModel.Concepto.isBlank()
                    MontoValidar = viewModel.Monto.isBlank()


                    if (viewModel.Monto.toString() == "") {
                        Toast.makeText(context, "El monto no puede estar vacio", Toast.LENGTH_SHORT)
                            .show()
                    }
                    if (viewModel.Concepto == "") {
                        Toast.makeText(context, "Telefono no debe estar vacio", Toast.LENGTH_SHORT)
                            .show()
                    }
                    if (viewModel.Balance.toString() == "") {
                        Toast.makeText(context, "El balance no puede estar vacio", Toast.LENGTH_SHORT)
                            .show()
                    }
                    if (!MontoValidar && !ConceptoValidar && !BalanceValidar) {
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