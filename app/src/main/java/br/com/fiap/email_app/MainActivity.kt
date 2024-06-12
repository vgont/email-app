package br.com.fiap.email_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import br.com.fiap.email_app.screens.InboxScreen
import br.com.fiap.email_app.screens.NewEmail
import br.com.fiap.email_app.screens.NewEvent
import br.com.fiap.email_app.screens.ReadEmail
import br.com.fiap.email_app.ui.theme.BackgroundColor
import br.com.fiap.email_app.ui.theme.EmailappTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EmailappTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = BackgroundColor
                ) {
                    val navController = rememberNavController()
                    NavHost(navController, startDestination = "inbox"){
                        composable(route = "inbox"){
                            InboxScreen(navController)
                        }
                        composable(route = "newEmail"){
                            NewEmail(navController)
                        }
                        composable(route = "readEmail"){
                            ReadEmail(navController)
                        }
                        composable(route = "newEvent"){
                            NewEvent(navController)
                        }
                    }
                }
            }
        }
    }
}


