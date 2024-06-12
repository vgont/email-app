package br.com.fiap.email_app.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.com.fiap.email_app.R
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.rememberMaterialDialogState

@Composable
fun InboxScreen(navController: NavController) {
    var isSideBarOpen by remember { mutableStateOf(value = false) }

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(vertical = 35.dp, horizontal = 12.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ) {
            SearchBar(isSideBarOpen, navController) {
                isSideBarOpen = it
            }
            Text(
                color = Color.White,
                text = "Geral",
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 10.dp, top = 25.dp, bottom = 10.dp)
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(state = rememberScrollState())
            ) {
                Email(navController)
                Email(navController)
                Email(navController)
                Email(navController)
                Email(navController)
                Email(navController)
                Email(navController)
                Email(navController)
                Email(navController)
                Email(navController)
            }
        }
        Button(
            onClick = { navController.navigate(route = "newEmail") },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .size(60.dp),
            shape = RoundedCornerShape(999.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFAD8F8F))
        ) {
            Text(
                text = "+",
                fontSize = 25.sp,
                textAlign = TextAlign.Center
            )
        }
    }
    if (isSideBarOpen) {
        SideBar() {
            isSideBarOpen = it
        }
    }
}

@Composable
fun SearchBar(isSideBarOpen: Boolean, navController: NavController, onSideBarToggle: (Boolean) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth(0.95f)
            .fillMaxHeight(0.095f)
            .background(color = Color(0xFFD9D9D9), shape = RoundedCornerShape(18.dp))
            .padding(horizontal = 15.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        IconButton(
            onClick = { onSideBarToggle(!isSideBarOpen) },
            enabled = !isSideBarOpen,
            modifier = Modifier.size(24.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_menu_24),
                contentDescription = "menu button",
                tint = Color.Black.copy(alpha = 0.5f),
                modifier = Modifier.fillMaxSize()
            )
        }
        Text(
            modifier = Modifier.padding(end = 120.dp),
            text = "Procurar email",
            fontSize = 16.sp,
            color = Color.Black.copy(alpha = 0.5f),
            textAlign = TextAlign.Center,
        )
        IconButton(onClick = { navController.navigate("newEvent") }) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_date_range_24),
                contentDescription = "menu button",
                tint = Color.Black.copy(alpha = 0.5f),
                modifier = Modifier.size(32.dp)
            )
        }
    }
}

@Composable
fun Email(navController: NavController) {
    var isFavorite by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color(0xFF464141), shape = RoundedCornerShape(16.dp))
            .padding(
                top = 9.dp,
                bottom = 9.dp,
                end = 8.dp,
            )
            .height(44.dp)
            .clickable { navController.navigate("readEmail") },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_account_circle_24),
                contentDescription = "UserIcon",
                modifier = Modifier.size(64.dp),
                tint = Color(0xFFD9D9D9)
            )
            Column(
                modifier = Modifier
                    .offset(
                        x = (-10).dp,
                    )
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                Text(
                    text = "Usuário",
                    fontSize = 18.sp,
                    color = Color.White,
                )
                Text(
                    text = "Assunto",
                    color = Color.White,
                    fontSize = 13.sp
                )
            }
        }
        FavoriteButton(isFavorite) {
            isFavorite = it
        }
    }
}

@Composable
fun FavoriteButton(isFavorite: Boolean, onFavToggle: (Boolean) -> Unit) {
    IconButton(
        onClick = { onFavToggle(!isFavorite) },
        modifier = Modifier
            .size(32.dp)
    ) {
        if (isFavorite) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_star_24),
                contentDescription = "unfav",
                tint = Color(0xFFD9D9D9),
                modifier = Modifier.fillMaxSize()
            )
        } else {
            Icon(
                painter = painterResource(id = R.drawable.baseline_star_border_24),
                contentDescription = "unfav",
                tint = Color(0xFFD9D9D9),
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Composable
fun SideBar(onSideBarToggle: (Boolean) -> Unit) {
    var isNewTagDialogOpen by remember { mutableStateOf(false) }
    Box(modifier = Modifier
        .background(Color.Black.copy(alpha = 0.45f))
        .fillMaxSize()
    ) {
        Row(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.7f)
                    .background(color = Color(0xFF1F1D1D))
            ) {
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.1f)
                        .padding(start = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "E-mails",
                        color = Color.White,
                        fontSize = 25.sp
                    )
                }
                Row (
                    modifier = Modifier
                        .border(BorderStroke(10.dp, color = Color.White))
                        .fillMaxWidth()
                        .height(1.dp)
                ){}
                Column(
                    modifier = Modifier
                        .padding(top = 52.dp, end = 40.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Tag(tagName = "Geral")
                    Tag(tagName = "Rascunhos")
                    Tag(tagName = "Spam")
                    Tag(tagName = "Lixeira")
                    NewTag(isNewTagDialogOpen) {
                        isNewTagDialogOpen = it
                    }

                }
            }
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .background(color = Color.Transparent)
            ) {
                Button(
                    onClick = { onSideBarToggle(false) },
                    modifier = Modifier
                        .fillMaxSize(),
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(Color.Transparent)
                ) {}
            }
        }
    }
    NewTagDialog(isNewTagDialogOpen) {
        isNewTagDialogOpen = !it
    }
}
@Composable
fun Tag(tagName: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(44.dp),
    ) {
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxSize()
                .offset(x = (-20).dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF322C2C)),
        ) {
            Text(
                text = tagName,
                textAlign = TextAlign.Left,
                fontSize = 20.sp,
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.CenterVertically)
                    .padding(start = 20.dp)
            )
        }
    }
}

@Composable
fun NewTag(isNewDialogOpen:Boolean, openNewTagDialog: (Boolean) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(44.dp),
    ) {
        Button(
            onClick = { openNewTagDialog(!isNewDialogOpen) },
            modifier = Modifier
                .fillMaxWidth(0.35f)
                .offset(x = (-20).dp)
                .fillMaxHeight(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF322C2C)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "+",
                fontSize = 20.sp,
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.CenterVertically)
                    .padding(start = 20.dp)
            )
        }
    }
}

@Composable
fun NewTagDialog(isOpen: Boolean, closeNewDialog: (Boolean) -> Unit) {
    var currentTagTitle by remember { mutableStateOf("") }
    var allFilters by remember { mutableStateOf(listOf<String>()) }
    val newTagDialogSate = rememberMaterialDialogState()

    if(isOpen) newTagDialogSate.show()

    MaterialDialog(
        dialogState = newTagDialogSate,
        buttons = {
            positiveButton(text = "adicionar", textStyle = TextStyle(color = Color.White))
            negativeButton(text = "cancelar",
                textStyle = TextStyle(color = Color.White),
                onClick = { closeNewDialog(false) }
            )
        },
        backgroundColor = Color(0xFF1F1D1D)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text(
                text = "Nova Tag",
                fontSize = 18.sp,
                color = Color.White,
                fontWeight = FontWeight.SemiBold
            )
            NewTagInput(label = "Nome da Tag", placeholder = "Escolha um nome para a tag", currentTagTitle) {
                currentTagTitle = it
            }
            NewTagFilter() {
                allFilters += it
            }
            AllFilters(filters = allFilters) {
                allFilters = it
            }
        }
    }
}

@Composable
fun NewTagInput(label: String, placeholder: String, currentValue: String, changeCurrentValue: (String) -> Unit) {
    Row {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = label,
                color = Color.White,
                modifier = Modifier.padding(start = 14.dp)
            )
            OutlinedTextField(
                placeholder = {
                    Text(text = placeholder,
                        color = Color(0xFF818181),
                        fontSize = 14.sp
                    )
                },
                value = currentValue,
                textStyle = TextStyle(Color.White),
                onValueChange = changeCurrentValue,
                colors = OutlinedTextFieldDefaults.colors(Color(0xFF2E2D2D)),
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
fun NewTagFilter(addNewFilter: (String) -> Unit) {
    var currentFilter by remember { mutableStateOf("") }
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(0.75f)
        ) {
            NewTagInput(label = "Novo Filtro", placeholder = "Adicione um filtro para a tag", currentFilter) {
                currentFilter = it
            }
        }
        Column(
            modifier = Modifier
                .align(Alignment.Bottom)
                .padding(bottom = 2.dp)
        ) {
            Button(
                onClick = {
                    addNewFilter(currentFilter)
                    currentFilter = ""
                },
                shape = RoundedCornerShape(100),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2E2D2D)),
                modifier = Modifier
                    .size(52.dp)
            ) {
                Text(text = "+",
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }

}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AllFilters(filters: List<String>, updateFilters: (List<String>) -> Unit) {
    var currentFilters by remember { mutableStateOf(filters) }
    FlowRow(
        maxItemsInEachRow = 3,
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        for (participant in filters) {
            FilterAdded(participant) {
                currentFilters = filters.filter { it != participant }
                updateFilters(currentFilters)
            }
        }
    }
}

@Composable
fun FilterAdded(filter: String, removeFilter: (String) -> Unit) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color(0xFF383838))
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 4.dp, horizontal = 8.dp)
        ) {
            Text(
                text = filter,
                color = Color.White
            )
            IconButton(
                onClick = { removeFilter(filter) },
                modifier = Modifier
                    .size(24.dp)
            ) {
                Icon(painter = painterResource(id = R.drawable.baseline_close_24), contentDescription = "close")
            }
        }
    }
}

@Preview()
@Composable
fun InboxPreview() {
    InboxScreen(rememberNavController())
}