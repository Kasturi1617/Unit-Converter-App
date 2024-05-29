package com.example.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnitConverterTheme {
                Surface (modifier = Modifier.fillMaxSize()) {
                    unitConverter()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun unitConverter() {

    var inputValue by remember {
        mutableStateOf("")
    }
    var outputValue by remember {
        mutableStateOf("")
    }
    var iDropDown by remember {
        mutableStateOf(false)
    }
    var oDropDown by remember {
        mutableStateOf(false)
    }
    var inputUnit by remember {
        mutableStateOf("Select")
    }
    var outputUnit by remember {
        mutableStateOf("Select")
    }
    var InputConversionFactor by remember {
        mutableStateOf(1.0)
    }
    var outputConversionFactor by remember {
        mutableStateOf(1.0)
    }

    fun convertUnit(){
        val inputDouble = inputValue.toDoubleOrNull()?:0.0
        val output = ((inputDouble * 100.0 * InputConversionFactor/ outputConversionFactor).roundToInt())/100.0
        outputValue = output.toString()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Unit Converter", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = inputValue,
            onValueChange = {
                inputValue = it
                convertUnit()
            },
            label = {Text(text = "Enter value")}
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Box {
                Button(onClick = { iDropDown = true}) {
                    Text(inputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "")
                    DropdownMenu(expanded = iDropDown, onDismissRequest = { iDropDown = false }) {
                        DropdownMenuItem(
                            text = { Text("Centimeters") },
                            onClick = {
                                iDropDown = false
                                inputUnit = "Centimeters"
                                InputConversionFactor = 0.01
                                convertUnit()
                            })
                        DropdownMenuItem(
                            text = { Text("Meters") },
                            onClick = {
                                iDropDown = false
                                inputUnit = "Meters"
                                InputConversionFactor = 1.0
                                convertUnit()
                            })
                        DropdownMenuItem(
                            text = { Text("Inches") },
                            onClick = {
                                iDropDown = false
                                inputUnit = "Inches"
                                InputConversionFactor = 0.0254
                                convertUnit()
                            })
                        DropdownMenuItem(
                            text = { Text("Feet") },
                            onClick = {
                                iDropDown = false
                                inputUnit = "Feet"
                                InputConversionFactor = 0.3048
                                convertUnit()
                            })
                        DropdownMenuItem(
                            text = { Text("Milimeters") },
                            onClick = {
                                iDropDown = false
                                inputUnit = "Milimeters"
                                InputConversionFactor = 0.001
                                convertUnit()
                            })
                    }
                }
            }
            Spacer(Modifier.width(16.dp))
            Box {
                Button(onClick = { oDropDown = true }) {
                    Text(outputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "")
                }
                DropdownMenu(expanded = oDropDown, onDismissRequest = { oDropDown = false }) {
                    DropdownMenuItem(
                        text = { Text("Centimeters") },
                        onClick = {
                            oDropDown = false
                            outputUnit = "Centimeters"
                            outputConversionFactor = 0.01
                            convertUnit()
                        })
                    DropdownMenuItem(
                        text = { Text("Meters") },
                        onClick = {
                            oDropDown = false
                            outputUnit = "Meters"
                            outputConversionFactor = 1.0
                            convertUnit()
                        })
                    DropdownMenuItem(
                        text = { Text("Inches") },
                        onClick = {
                            oDropDown = false
                            outputUnit = "Inches"
                            outputConversionFactor = 0.0254
                            convertUnit()
                        })
                    DropdownMenuItem(
                        text = { Text("Feet") },
                        onClick = {
                            oDropDown = false
                            outputUnit = "Feet"
                            outputConversionFactor = 0.3048
                            convertUnit()
                        })
                    DropdownMenuItem(
                        text = { Text("Milimeters") },
                        onClick = {
                            oDropDown = false
                            outputUnit = "Milimeters"
                            outputConversionFactor = 0.001
                            convertUnit()
                        })
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Result: $outputValue",
            style = MaterialTheme.typography.headlineMedium)
    }
}