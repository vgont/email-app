package br.com.fiap.email_app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.com.fiap.email_app.R
import br.com.fiap.email_app.ui.theme.BackgroundColor
import br.com.fiap.email_app.ui.theme.EmailappTheme

@Composable
fun NewEmail(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ActivityBar(navController)
            Spacer(modifier = Modifier.size(25.dp))
            PersonEmail(campo = "De", email = "email_do_usuario@email.com.br")
            PersonEmail(campo = "Para", email = "email_do_destinatario@email.com.br")
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(49.dp)
                    .padding(start = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Assunto", color = Color.Gray)
            }
            Row(modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .height(1.dp)){}
            Row(
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(10.dp)
            ){
                Text(
                    text = "Escrever Email",
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
fun ActivityBar(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.05f),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(
            onClick = { navController.navigate(route = "inbox") },
            modifier = Modifier
                .size(36.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                contentDescription = "back",
                tint = Color.White,
                modifier = Modifier
                    .fillMaxSize()
            )
        }
        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .size(36.dp)
                .offset(x = 7.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_more_vert_24),
                contentDescription = "back",
                tint = Color.White,
                modifier = Modifier
                    .fillMaxSize()
            )
        }
    }
}

@Composable
fun PersonEmail(campo: String, email: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(49.dp)
            .padding(start = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row() {
            Text(
                text = "$campo: ",
                textAlign = TextAlign.Center,
                color = Color.White,
            )
            Text(
                text = email,
                textAlign = TextAlign.Center,
                color = Color.White,
                textDecoration = TextDecoration.Underline
            )
        }
    }
    Row(modifier = Modifier
        .fillMaxWidth()
        .background(Color.White)
        .height(1.dp)){}
}
@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun NewEmailPreview() {
    EmailappTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = BackgroundColor
        ) {
            val navController = rememberNavController()
            NewEmail(navController)
        }
    }
}