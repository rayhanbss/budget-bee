package com.example.budgetbee

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.tooling.preview.Preview
import com.example.budgetbee.TransactionScreen
import com.example.budgetbee.transactions
import com.example.budgetbee.ui.theme.BudgetBeeTheme
import com.example.budgetbee.ui.theme.BudgetBeeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BudgetBeeTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    TransactionScreen(transactions)
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BudgetBeeTheme {
        TransactionScreen(transactions)
    }
}