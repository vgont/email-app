package br.com.fiap.email_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
fun Inbox() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 35.dp, horizontal = 12.dp)
    ) {
        SearchBar()
        Text(text = "Geral", modifier = Modifier
            .align(Alignment.Start)
            .padding(start = 10.dp, top = 25.dp, bottom = 10.dp))
        Email()
    }
}

@Composable
fun SearchBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth(0.95f)
            .fillMaxHeight(0.095f)
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

@Composable
fun Email(modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.12f)
            .background(color = Color(0xFF464141), shape = RoundedCornerShape(16.dp))
            .padding(
                vertical = 9.dp,
                horizontal = 8.dp
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            painter = painterResource(id = R.drawable.baseline_account_circle_24),
            contentDescription = "user",
            modifier = Modifier.size(64.dp),
            tint = Color(0xFFD9D9D9)
        )
        Column(
            modifier = Modifier
                .offset(
                    x = (-10).dp,
                ),
        ) {
            Text(
                text = "Usuário",
                fontSize = 20.sp,
                color = Color.White,
            )
            Text(
                text = "Assunto",
                color = Color.White,
                fontSize = 15.sp
            )
        }
        Icon(
            painter = painterResource(id = R.drawable.baseline_star_border_24),
            contentDescription = "unfav",
            tint = Color(0xFFD9D9D9),
            modifier = Modifier.size(32.dp),
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
