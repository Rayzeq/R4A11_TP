package com.example.tp3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tp3.ui.theme.TP3HostNavTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TP3HostNavTheme {
                MaterialTheme {
                    AppNavigation()
                }
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "home") {
        composable("home") {
            HomeScreen(navController)
        }
        composable("form") {
            FormScreen(navController)
        }
        composable(
            "display/{name}&{age}",
            arguments = listOf(
                navArgument("name") { defaultValue = "" },
                navArgument("age") { defaultValue = 0 })
        ) { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: ""
            val age = backStackEntry.arguments?.getInt("age") ?: 0
            DisplayScreen(navController, name, age)
        }
    }
}

@Composable
fun HomeScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Bienvenue dans ma première application compose navigation",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(25.dp))
        Button(onClick = { navController.navigate("form") }) {
            Text(text = "Accéder au formulaire")
        }
    }
}

@Composable
fun FormScreen(navController: NavHostController) {
    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Page du formulaire.", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(24.dp))
        TextField(
            value = name,
            onValueChange = { newText -> name = newText },
            label = { Text("Entrez votre nom") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        TextField(
            value = age,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = { newText -> age = newText },
            label = { Text("Entrez votre age") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        Button(onClick = { navController.navigate("display/$name&${age.toInt()}") }) { Text(text = "Valider") }
        Button(onClick = { navController.popBackStack() }) { Text(text = "Retour") }
    }
}

@Composable
fun DisplayScreen(navController: NavHostController, name: String, age: Int) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Bienvenue",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(25.dp))
        Text(
            text = name,
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(25.dp))
        Text(
            text = "Vous avez $age ans !",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(25.dp))
        Button(onClick = { navController.popBackStack() }) {
            Text(text = "Retour")
        }
    }
}