package com.example.budgetbee

import android.os.Bundle
import android.util.Log
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.budgetbee.data.repository.AuthRepository
import com.example.budgetbee.data.repository.CategoryRepository
import com.example.budgetbee.data.repository.TokenRepository
import com.example.budgetbee.data.repository.TransactionRepository
import com.example.budgetbee.data.repository.UserRepository
import com.example.budgetbee.ui.component.AddOptionsBottomSheet
import com.example.budgetbee.ui.component.NavBar
import com.example.budgetbee.ui.screen.auth.*
import com.example.budgetbee.ui.screen.main.*
import com.example.budgetbee.ui.theme.*
import com.example.budgetbee.viewmodel.AuthViewModel
import com.example.budgetbee.viewmodel.AuthViewModelFactory
import com.example.budgetbee.viewmodel.TransactionViewModel
import com.example.budgetbee.viewmodel.TransactionViewModelFactory
import com.example.budgetbee.viewmodel.UserViewModel
import com.example.budgetbee.viewmodel.UserViewModelFactory

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val splashScreen = installSplashScreen()
        val isCheckingAuth = mutableStateOf(true)
        splashScreen.setKeepOnScreenCondition { isCheckingAuth.value }
        setContent {
            BudgetBeeTheme {
                val context = LocalContext.current.applicationContext

                // Initialize repositories
                val tokenRepository = remember { TokenRepository(context) }
                val userRepository = remember { UserRepository(context) }
                val categoryRepository = remember { CategoryRepository(context) }
                val authRepository = remember { AuthRepository(context) }
                val transactionRepository = remember { TransactionRepository(context) }

                // Initialize ViewModels with factories
                val userFactory = remember { UserViewModelFactory(userRepository) }
                val userViewModel: UserViewModel = viewModel(factory = userFactory)

                val authFactory = remember { AuthViewModelFactory(authRepository, tokenRepository, categoryRepository, userRepository) }
                val authViewModel: AuthViewModel = viewModel(factory = authFactory)

                // Collect token and user state
                val tokenState by authViewModel.tokenState.collectAsState()
                val tokenString = tokenState?.value
                val userState = userViewModel.userState.collectAsState()
                val user = userState.value
                Log.i("MainActivity", "Token: $tokenString, User: $user")

                val transactionFactory = remember { TransactionViewModelFactory(transactionRepository) }
                val transactionViewModel: TransactionViewModel = viewModel(factory = transactionFactory)

                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route ?: ""

                val remoteSuccess = userViewModel.remoteSuccess.value

                LaunchedEffect(tokenString, user, remoteSuccess, currentRoute) {
                    Log.i("MainActivity", "LaunchedEffect: token=$tokenString, user=$user, remoteSuccess=$remoteSuccess, route=$currentRoute")
                    if (tokenString != null && user?.id != null && !remoteSuccess) {
                        userViewModel.getUserRemote(user.id.toString(), tokenString)
                    } else if ((tokenString == null || user == null) &&
                        currentRoute !in listOf("login", "register", "launch", "forgot")
                    ) {
                        Log.e("MainActivity", "Token or user state is null, Token: $tokenString, User: $user")
                        navController.navigate("launch")
                    } else if (remoteSuccess && tokenString != null && user != null && currentRoute == "launch") {
                        Log.i("MainActivity", "User remote fetched successfully, navigating to dashboard")
                        navController.navigate("dashboard") {
                            popUpTo("launch") { inclusive = true }
                        }
                        transactionViewModel.getAllTransactions(user, tokenString)
                    }
                    kotlinx.coroutines.delay(1300)
                    isCheckingAuth.value = false
                }

                val showAddOptionsBottomSheet = remember { mutableStateOf(false) }
                val addOptionsBottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
                val isTransactionCreated = transactionViewModel.isTransactionCreated
                val isTransactionUpdated = transactionViewModel.isTransactionUpdated

                LaunchedEffect(isTransactionCreated, isTransactionUpdated) {
                    if (isTransactionCreated || isTransactionUpdated) {
                        transactionViewModel.getAllTransactions(user, tokenString)
                        transactionViewModel.isTransactionCreated = false
                        transactionViewModel.isTransactionUpdated = false
                    }
                }

                if (showAddOptionsBottomSheet.value) {
                    ModalBottomSheet(
                        onDismissRequest = { showAddOptionsBottomSheet.value = false },
                        containerColor = White,
                        sheetState = addOptionsBottomSheetState,
                        modifier = Modifier.wrapContentHeight()
                    ) {
                        AddOptionsBottomSheet(
                            onAddTransaction = {
                                showAddOptionsBottomSheet.value = false
                                navController.navigate("add_transaction")
                            },
                            onAddCategory = {
                                showAddOptionsBottomSheet.value = false
                                // navController.navigate("add_category")
                            },
                            onAddTarget = {
                                showAddOptionsBottomSheet.value = false
                                // navController.navigate("add_target")
                            }
                        )
                    }
                }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        if (currentRoute !in listOf("login", "register", "launch", "forgot", "add_transaction", "edit_transaction/{transactionId}")) {
                            NavBar(navController, currentRoute, showAddOptionsBottomSheet)
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
                            startDestination = "launch"
                        ) {
                            composable("dashboard") { DashboardScreen(userViewModel, transactionViewModel, tokenString, navController) }
                            composable("transaction") { TransactionScreen(transactionViewModel, user, tokenString, navController) }
                            composable("target") { TargetScreen() }
                            composable("profile") { ProfileScreen(context, navController, authViewModel, userViewModel) }
                            composable("login") { LoginScreen(navController, authViewModel) }
                            composable("register") { RegisterScreen(navController, authViewModel) }
                            composable("launch") { LaunchPage(navController)}
                            composable("forgot") { ForgotPasswordScreen(navController) }
                            composable("add_transaction") {
                                AddTransactionScreen(
                                    navController = navController,
                                    transactionViewModel = transactionViewModel,
                                    user = user,
                                    tokenString = tokenString
                                )
                            }
                            composable("edit_transaction/{transactionId}") { backStackEntry ->
                                val transactionId = backStackEntry.arguments?.getString("transactionId")
                                val transaction = transactionViewModel.transactions.find { it.id == transactionId }
                                if (transaction != null) {
                                    EditTransactionScreen(
                                        navController = navController,
                                        transactionViewModel = transactionViewModel,
                                        user = user,
                                        tokenString = tokenString,
                                        transaction = transaction
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
