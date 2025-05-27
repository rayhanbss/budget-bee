package com.example.budgetbee

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.budgetbee.ui.component.NavBar
import com.example.budgetbee.ui.screen.auth.LoginScreen
import com.example.budgetbee.ui.screen.auth.RegisterScreen
import com.example.budgetbee.ui.screen.main.DashboardScreen
import com.example.budgetbee.ui.screen.main.ProfileScreen
import com.example.budgetbee.ui.screen.main.TargetScreen
import com.example.budgetbee.ui.screen.main.TransactionScreen
import com.example.budgetbee.ui.theme.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BudgetBeeTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route ?: ""

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        if (currentRoute !in listOf("login", "register")) {
                            NavBar(navController, currentRoute)
                        }
                    }
                ) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        NavHost(
                            navController = navController,
                            startDestination = "login"
                        ) {
                            composable("dashboard") { DashboardScreen() }
                            composable("transaction") { TransactionScreen() }
                            composable("target") { TargetScreen() }
                            composable("profile") { ProfileScreen() }
                            composable("login") { LoginScreen(navController) }
                            composable("register") { RegisterScreen(navController) }
                        }
                    }
                }
            }
        }
    }
}
