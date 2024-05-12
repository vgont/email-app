package br.com.fiap.email_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.fiap.email_app.ui.theme.BackgroundColor
import br.com.fiap.email_app.ui.theme.EmailappTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EmailappTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = BackgroundColor
                ) {
                    Inbox()
                }
            }
        }
    }
}

@Composable
fun Inbox(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 35.dp, start = 12.dp, end = 12.dp,),
        contentAlignment = Alignment.TopCenter,

    ) {
        SearchBar()

    }
}

@Composable
fun SearchBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .fillMaxHeight(0.07f)
            .background(color = Color(0xFFD9D9D9), shape = RoundedCornerShape(18.dp))
            .padding(horizontal = 15.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.baseline_menu_24),
            contentDescription = "menu button",
            tint = Color.Black.copy(alpha = 0.5f),
        )
        Text(
            text = "Procurar email",
            color = Color.Black.copy(alpha = 0.5f),
            textAlign = TextAlign.Center,
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun InboxPreview() {
    EmailappTheme {
        Inbox()
    }
}