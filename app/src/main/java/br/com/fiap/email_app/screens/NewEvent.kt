package br.com.fiap.email_app.screens

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.com.fiap.email_app.R
import br.com.fiap.email_app.ui.theme.BackgroundColor
import br.com.fiap.email_app.ui.theme.EmailappTheme
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.MaterialDialogState
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.datetime.time.timepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import com.vanpra.composematerialdialogs.title
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Composable
fun NewEvent(navController: NavController) {
    var allParticipants by remember { mutableStateOf(listOf<String>()) }

    Box(modifier = Modifier.fillMaxSize()){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ActivityBar(navController)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.1f)
            ) {
                Text(
                    text = "Novo Evento",
                    fontSize = 25.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Medium
                )
            }
            Spacer(modifier = Modifier.size(16.dp))
            Column(
                verticalArrangement = Arrangement.spacedBy(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .verticalScroll(rememberScrollState())

            ) {
                EventInput(
                    label = "Título do Evento *",
                    placeholder = "Adicione um título para o evento"
                )
                EventInput(
                    label = "Descrição do Evento *",
                    placeholder = "Adicione uma descrição para o evento"
                )
                DateTimePicker()
                EventInput(
                    label = "Local do Evento",
                    placeholder = "Adicione um local para o evento"
                )
                AddParticipants() {
                    allParticipants += it
                }
                AllParticipants(allParticipants) {
                    allParticipants = it
                }
                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(Color(0xFF3F3D3D)),
                    modifier = Modifier
                        .padding(12.dp)
                ) {
                    Text(text = "Adicionar Evento")
                }
            }
        }
    }
}

@Composable
fun EventInput(label: String, placeholder: String) {
    Row {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = label,
                color = Color.White
            )
            OutlinedTextField(
                placeholder = {
                    Text(text = placeholder,
                        color = Color(0xFF818181),
                        fontSize = 15.sp
                    )
                },
                value = "",
                textStyle = TextStyle(Color.White),
                onValueChange = {},
                colors = OutlinedTextFieldDefaults.colors(Color(0xFF2E2D2D)),
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
fun DateTimePicker() {
    var pickedDate by remember { mutableStateOf(LocalDate.now()) }
    val formattedDate by remember {
        derivedStateOf {
            DateTimeFormatter
                .ofPattern("dd/MM/YY")
                .format(pickedDate)
        }
    }

    var startsAt by remember { mutableStateOf(LocalTime.NOON) }
    val formattedstartsAt by remember {
        derivedStateOf {
            DateTimeFormatter
                .ofPattern("kk:mm")
                .format(startsAt)
        }
    }
    var endsAt by remember { mutableStateOf(LocalTime.MAX) }
    val formattedEndsAt by remember {
        derivedStateOf {
            DateTimeFormatter
                .ofPattern("kk:mm")
                .format(endsAt)
        }
    }

    val dateDialogState = rememberMaterialDialogState()
    val startsAtDialogState = rememberMaterialDialogState()
    val endsAtDialogState = rememberMaterialDialogState()

    Column(
        modifier = Modifier.height(88.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Data e Horário do Evento *",
                    color = Color.White
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Card(
                        colors = CardDefaults.cardColors(Color.Transparent),
                        border = BorderStroke(width = 1.dp, color = Color.Gray),
                    ) {
                        Text(
                            text = formattedDate,
                            color = Color.White,
                            modifier = Modifier
                                .padding(16.dp)
                        )
                    }
                    Spacer(modifier = Modifier.size(4.dp))
                    IconButton(onClick = { dateDialogState.show() }, modifier = Modifier.size(20.dp)) {
                        Icon(painter = painterResource(id = R.drawable.baseline_date_range_24), contentDescription = "calendario", tint = Color.White)
                    }
                    Spacer(modifier = Modifier.size(10.dp))
                    EventTime(formattedstartsAt, startsAtDialogState)
                    Spacer(modifier = Modifier.size(10.dp))
                    EventTime(formattedEndsAt, endsAtDialogState)
                }
            }
        }
        MaterialDialog(
            dialogState = dateDialogState,
            buttons = {
                positiveButton(text = "ok")
                negativeButton(text = "cancelar")
            }
        ) {
            datepicker(
                initialDate = pickedDate,
                title = "Data do Evento",
            ) {
                pickedDate = it
            }
        }
        MaterialDialog(
            dialogState = startsAtDialogState,
            buttons = {
                positiveButton(text = "ok")
                negativeButton(text = "cancelar")
            }
        ) {
            timepicker(
                initialTime = LocalTime.NOON,
                title = "Horário de Inicio",
            ) {
                startsAt = it
            }
        }
        MaterialDialog(
            dialogState = endsAtDialogState,
            buttons = {
                positiveButton(text = "ok")
                negativeButton(text = "cancelar")
            }
        ) {
            timepicker(
                initialTime = LocalTime.NOON,
                title = "Horário de Inicio",
            ) {
                endsAt = it
            }
        }
    }
}

@Composable
fun EventTime(time: String, dialogToggle: MaterialDialogState) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Card(
            colors = CardDefaults.cardColors(Color.Transparent),
            border = BorderStroke(width = 1.dp, color = Color.Gray),
        ) {
            Text(
                text = time,
                color = Color.White,
                modifier = Modifier
                    .padding(16.dp)
            )
        }
        Spacer(modifier = Modifier.size(4.dp))
        IconButton(onClick = { dialogToggle.show() }, modifier = Modifier.size(20.dp)) {
            Icon(painter = painterResource(id = R.drawable.baseline_access_time_24), contentDescription = "calendario", tint = Color.White)
        }
    }
}

@Composable
fun AddParticipants(AddParticipant: (String)-> Unit) {
    var currentParticipant by remember { mutableStateOf("") }
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Participantes do Evento",
                color = Color.White,
            )
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                OutlinedTextField(
                    placeholder = {
                        Text(
                            text = "Ex: participante1@email.com",
                            color = Color(0xFF818181),
                            fontSize = 15.sp
                        )
                    },
                    value = currentParticipant,
                    onValueChange = {
                        currentParticipant = it
                    },
                    colors = OutlinedTextFieldDefaults.colors(Color(0xFF2E2D2D)),
                    shape = RoundedCornerShape(24.dp),
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                )
                TextButton(
                    onClick = {
                        if (isValidEmail(currentParticipant)){
                            AddParticipant(currentParticipant)
                            currentParticipant = ""
                        }
                    },
                    shape = RoundedCornerShape(100),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2E2D2D)),
                    modifier = Modifier.height(58.dp)
                ) {
                    Text(
                        text = "+",
                        fontSize = 20.sp
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AllParticipants(participants: List<String>, updateList: (List<String>) -> Unit) {
    var currentParticipants by remember { mutableStateOf(participants) }
    FlowRow(
        maxItemsInEachRow = 3,
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        for (participant in participants) {
            ParticipantAdded(participant) {
                currentParticipants = participants.filter { it != participant }
                updateList(currentParticipants)
            }
        }
    }
}

@Composable
fun ParticipantAdded(participant: String, removeParticipant: (String) -> Unit) {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color(0xFF383838))
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 4.dp, horizontal = 8.dp)
        ) {
            Text(
                text = participant,
                color = Color.White
            )
            IconButton(
                onClick = { removeParticipant(participant) },
                modifier = Modifier
                    .size(24.dp)
            ) {
                Icon(painter = painterResource(id = R.drawable.baseline_close_24), contentDescription = "close")
            }
        }
    }
}

fun isValidEmail(email: String): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun NewEventPreview() {
    EmailappTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = BackgroundColor
        ) {
            val navController = rememberNavController()
            NewEvent(navController)
        }
    }
}