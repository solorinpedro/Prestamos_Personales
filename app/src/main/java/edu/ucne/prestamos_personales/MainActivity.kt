package edu.ucne.prestamos_personales

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import edu.ucne.prestamos_personales.ui.componentes.DashBoard
import edu.ucne.prestamos_personales.ui.componentes.RegistroOcupacion
import edu.ucne.prestamos_personales.ui.componentes.RegistroPersona
import edu.ucne.prestamos_personales.ui.componentes.RegistroPrestamo
import edu.ucne.prestamos_personales.ui.theme.Prestamos_PersonalesTheme
import edu.ucne.prestamos_personales.util.Screen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp() {
    Prestamos_PersonalesTheme() {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            val navHostController = rememberNavController()
            NavHost(
                navController = navHostController,
                startDestination = Screen.DashBoardScreen.route
            ) {

                composable(route = Screen.DashBoardScreen.route) {
                    DashBoard(goRegistroPersonas = { navHostController.navigate(Screen.RegistroPersonaScreen.route) },
                        goRegistroOcupaciones = { navHostController.navigate(Screen.RegistroOcupaciones.route) },
                        goRegistroPrestamo = { navHostController.navigate(Screen.RegistroPrestamosScreen.route) })
                }

                composable(route = Screen.RegistroPersonaScreen.route) {
                    RegistroPersona(backToDashboard = { navHostController.navigate(Screen.DashBoardScreen.route) })
                }

                composable(route = Screen.RegistroOcupaciones.route) {
                    RegistroOcupacion(backToDashboard = { navHostController.navigate(Screen.DashBoardScreen.route) })
                }
                composable(route = Screen.RegistroPrestamosScreen.route) {
                    RegistroPrestamo(backToDashboard = { navHostController.navigate(Screen.DashBoardScreen.route) })
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Prestamos_PersonalesTheme {
        MyApp()
    }
}