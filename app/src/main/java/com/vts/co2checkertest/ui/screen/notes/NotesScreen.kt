package com.vts.co2checkertest.ui.screen.notes

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.vts.co2checkertest.R
import com.vts.co2checkertest.constants.TransportType
import com.vts.co2checkertest.ui.component.CustomLazyColumn
import com.vts.co2checkertest.ui.component.ScaffoldWithDrawer
import com.vts.co2checkertest.ui.theme.Blue
import com.vts.co2checkertest.ui.theme.ErrorRed
import com.vts.co2checkertest.ui.theme.Gray
import com.vts.co2checkertest.ui.theme.LabelText
import com.vts.co2checkertest.ui.theme.Red
import com.vts.co2checkertest.ui.theme.SelectionBlue
import com.vts.co2checkertest.ui.theme.SemiLightGray
import com.vts.co2checkertest.ui.theme.Typography
import com.vts.co2checkertest.ui.theme.White
import com.vts.co2checkertest.utils.convertStringToDouble
import com.vts.co2checkertest.utils.formatDistanceInput
import com.vts.co2checkertest.utils.validateDistanceInput
import com.vts.co2checkertest.utils.validateTransportSelection

@Composable
fun NotesScreen(navController: NavController, title: String, notesViewModel: NotesViewModel = viewModel()) {

    val categories = TransportType.entries.map { it.displayName }

    var expanded by remember { mutableStateOf(false) }
    var transportFieldColor by remember { mutableStateOf(SemiLightGray) }
    var distanceFieldColor by remember { mutableStateOf(SemiLightGray) }
    var transportErrorText by remember { mutableStateOf("") }
    var distanceErrorText by remember { mutableStateOf("") }

    var selectedTransport by remember { mutableStateOf("") }
    var distance by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        notesViewModel.getAllTrips()
    }

    val userTrips by remember { notesViewModel.trips }

    ScaffoldWithDrawer(title = title, navController = navController) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 8.dp
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.add_note_text),
                        style = Typography.bodyLarge,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(transportFieldColor, RoundedCornerShape(12.dp))
                            .clickable { expanded = !expanded }
                            .padding(horizontal = 12.dp, vertical = 12.dp)
                            .height(32.dp)
                    ) {
                        Text(
                            text = if (selectedTransport.isEmpty()) stringResource(id = R.string.transport_type_label) else selectedTransport,
                            color = if (selectedTransport.isEmpty()) LabelText else Gray,
                            style = Typography.bodyLarge,
                            modifier = Modifier.align(Alignment.CenterStart)
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.ic_drop_down),
                            contentDescription = "Dropdown Icon",
                            tint = Gray,
                            modifier = Modifier.align(Alignment.CenterEnd)
                        )

                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false },
                            modifier = Modifier
                                .background(SemiLightGray)
                                .width(200.dp)
                        ) {
                            categories.forEach { type ->
                                DropdownMenuItem(
                                    text = {
                                        Text(
                                            text = type,
                                            style = Typography.bodyLarge
                                        )
                                    },
                                    onClick = {
                                        selectedTransport = type
                                        expanded = false
                                    },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(SemiLightGray)
                                )
                            }
                        }
                    }

                    if (transportErrorText.isNotEmpty()) {
                        Text(
                            text = transportErrorText,
                            color = Red,
                            style = Typography.bodyMedium
                        )
                    }

                    TextField(
                        value = distance,
                        onValueChange = { distance = it },
                        label = {
                            Text(
                                text = stringResource(id = R.string.distance_value_label),
                                color = LabelText
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(SemiLightGray, RoundedCornerShape(12.dp)),
                        shape = RoundedCornerShape(12.dp),
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = Gray,
                            unfocusedTextColor = Gray,
                            focusedContainerColor = distanceFieldColor,
                            unfocusedContainerColor = distanceFieldColor,
                            cursorColor = Gray,
                            selectionColors = TextSelectionColors(
                                handleColor = Gray,
                                backgroundColor = SelectionBlue
                            ),
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent
                        ),
                        singleLine = true
                    )

                    if (distanceErrorText.isNotEmpty()) {
                        Text(
                            text = distanceErrorText,
                            color = Red,
                            style = Typography.bodyMedium
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Absolute.SpaceBetween
                    ) {
                        OutlinedButton(
                            onClick = {
                                selectedTransport = ""
                                distance = ""
                            },
                            modifier = Modifier.weight(1f),
                            border = BorderStroke(2.dp, Gray),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text(
                                text = stringResource(id = R.string.clear_button_text),
                                style = Typography.bodyLarge
                            )
                        }

                        Spacer(modifier = Modifier.width(16.dp))

                        OutlinedButton(
                            onClick = {
                                if (!validateTransportSelection(selectedTransport)) {
                                    transportFieldColor = ErrorRed
                                    transportErrorText = "Chose transport"
                                }

                                if (!validateDistanceInput(distance)) {
                                    distanceFieldColor = ErrorRed
                                    distanceErrorText = "Invalid distance"
                                } else {
                                    transportFieldColor = SemiLightGray
                                    distanceFieldColor = SemiLightGray
                                    transportErrorText = ""
                                    distanceErrorText = ""

                                    val formattedDistance = formatDistanceInput(distance)

                                    notesViewModel.addNewTrip(selectedTransport, formattedDistance.toDouble())

                                    selectedTransport = ""
                                    distance = ""
                                }
                            },
                            modifier = Modifier.weight(1f),
                            colors = ButtonDefaults.outlinedButtonColors(contentColor = Blue),
                            border = BorderStroke(2.dp, Blue),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text(
                                text = stringResource(id = R.string.add_button_text),
                                style = Typography.bodyLarge,
                                color = Blue
                            )
                        }
                    }
                }
            }

            Text(
                text = stringResource(id = R.string.latest_notes_text),
                style = Typography.bodyLarge,
                modifier = Modifier.padding(start = 12.dp)
            )

            CustomLazyColumn(trips = userTrips, 5)
        }
    }
}