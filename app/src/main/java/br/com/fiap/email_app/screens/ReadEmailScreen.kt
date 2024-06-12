package br.com.fiap.email_app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.com.fiap.email_app.R
import br.com.fiap.email_app.ui.theme.BackgroundColor
import br.com.fiap.email_app.ui.theme.EmailappTheme

@Composable
fun ReadEmail(navController: NavController) {
    var isFavorite by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ActivityBar(navController = navController)
            Spacer(modifier = Modifier.size(25.dp))
            Column(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(88.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(0.85f)
                            .fillMaxHeight()
                    ) {
                        Text( //max char = 82
                            text = "Consectetur dolor sit amet, consectetur adipiscing elit, sed do eiusmod temporadaf",
                            color = Color.White,
                            fontSize = 22.sp,
                            modifier = Modifier
                                .fillMaxHeight()
                        )
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        FavoriteButton(isFavorite = isFavorite) {
                            isFavorite = it
                        }
                    }
                }
                Spacer(modifier = Modifier.size(25.dp))
                Column(
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                    modifier = Modifier
                        .verticalScroll(state = rememberScrollState())
                ) {
                    Email(user = "Usuário 1")
                    Email(user = "Usuário 2")
                }
                
            }
        }
    }
}

@Composable
fun Email(user: String) {
    var isOpenedEmail by remember { mutableStateOf(false) }

    if (isOpenedEmail) {
        OpenedEmail(user, isOpenedEmail) {
            isOpenedEmail = it
        }
    } else {
        ClosedEmail(user, isOpenedEmail) {
            isOpenedEmail = it
        }
    }
}
@Composable
fun OpenedEmail(user: String, isOpenedEmail: Boolean, onOpenToggle: (Boolean) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(440.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF323232))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.15f)
                    .clickable { onOpenToggle(!isOpenedEmail) },
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_account_circle_24),
                        contentDescription = "user",
                        modifier = Modifier.size(56.dp),
                        tint = Color(0xFFD9D9D9)
                    )
                    Spacer(modifier = Modifier.size(4.dp))
                    Column(
                        verticalArrangement = Arrangement.spacedBy(1.dp),
                        modifier = Modifier
                            .height(44.dp)
                    ) {
                        Text(
                            text = user,
                            fontSize = 20.sp,
                            color = Color.White
                        )
                        Text(
                            text = "para pessoa_destinada",
                            fontSize = 10.sp,
                            color = Color.White
                        )
                    }
                }
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .height(44.dp)
                ) {
                    Text(
                        text = "DD/MM/YYYY",
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally),
                        textAlign = TextAlign.Center,
                        fontSize = 12.sp,
                        color = Color.White
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
            Row(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(bottom = 18.dp)
            ) {
                Column(
                    modifier = Modifier
                        .verticalScroll(state = rememberScrollState())
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam",
                        color = Color.White,
                    )
                }
            }
        }
    }
}
@Composable
fun ClosedEmail(user: String, isOpenedEmail: Boolean, onOpenToggle: (Boolean) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color(0xFF323232), shape = RoundedCornerShape(16.dp))
            .padding(
                top = 9.dp,
                bottom = 9.dp,
                end = 8.dp,
            )
            .height(52.dp)
            .clickable { onOpenToggle(!isOpenedEmail) },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_account_circle_24),
                contentDescription = "UserIcon",
                modifier = Modifier.size(64.dp),
                tint = Color(0xFFD9D9D9)
            )
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.4f)
                        .padding(end = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = user,
                        fontSize = 16.sp,
                        color = Color.White,
                    )
                    Text(
                        text = "DD/MM/YYYY",
                        color = Color.White,
                        fontSize = 12.sp,
                        modifier = Modifier
                    )
                }
                Row {
                    Text(
                        text = "Sed ut perspiciatis unde omnis iste natus...",
                        color = Color.White,
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ReadEmailPreview() {
    EmailappTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = BackgroundColor
        ) {
            val navController = rememberNavController()
            ReadEmail(navController)
        }
    }
}