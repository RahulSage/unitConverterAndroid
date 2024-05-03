package com.example.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    UnitConverter()
                }
            }
        }
    }
}

@Composable
fun UnitConverter(){

    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var iExpand by remember { mutableStateOf(false) }
    var oExpand by remember { mutableStateOf(false) }
    var inputUnit by remember { mutableStateOf("Meter") }
    var outputUnit by remember { mutableStateOf("Meter") }
    var conversionFactor by remember { mutableStateOf(1.0) }
    var oConversionFactor by remember { mutableStateOf(1.0) }

    fun convertUnit(){
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0;
        val result = (inputValueDouble * conversionFactor * 100.0 / oConversionFactor).roundToInt() / 100.0;
        outputValue = result.toString();

    }

    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Unit Convertor", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = inputValue, onValueChange = {
            inputValue = it;
            convertUnit()
        })
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Box {
                Button(onClick = { iExpand = true;
                        }) {
                    Text(text = inputUnit);
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "")
                }
                DropdownMenu(expanded = iExpand, onDismissRequest = { iExpand = false }) {
                    DropdownMenuItem(text = { Text(text = "Centimeter")}, onClick = { /*TODO*/
                        iExpand = false;
                        inputUnit = "Centimeter"
                        conversionFactor = 0.01
                        convertUnit()
                    })
                    DropdownMenuItem(text = { Text(text = "Meters")}, onClick = {
                        iExpand = false;
                        inputUnit = "Meters"
                        conversionFactor = 1.0
                        convertUnit()
                    })
                    DropdownMenuItem(text = { Text(text = "Feet")}, onClick = {
                        iExpand = false;
                        inputUnit = "Feet"
                        conversionFactor = 0.3048
                        convertUnit()
                    })
                    DropdownMenuItem(text = { Text(text = "Kilometer")}, onClick = {
                        iExpand = false;
                        inputUnit = "Kilometer"
                        conversionFactor = 1000.0
                        convertUnit()
                    })
                    
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Box {
                Button(onClick = { oExpand = true; }) {
                    Text(text = outputUnit);
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "")
                }
                DropdownMenu(expanded = oExpand, onDismissRequest = { oExpand = false ;}) {
                    DropdownMenuItem(text = { Text(text = "Centimeter")}, onClick = {
                        oExpand = false;
                        outputUnit = "Centimeter"
                        oConversionFactor = 0.01
                        convertUnit()
                    })
                    DropdownMenuItem(text = { Text(text = "Meters")}, onClick = {
                        oExpand = false;
                        outputUnit = "Meters"
                        oConversionFactor = 1.0
                        convertUnit()
                    })
                    DropdownMenuItem(text = { Text(text = "Feet")}, onClick = {
                        oExpand = false;
                        outputUnit = "Feet"
                        oConversionFactor = 0.3048
                        convertUnit()
                    })
                    DropdownMenuItem(text = { Text(text = "Kilometer")}, onClick = {
                        oExpand = false;
                        outputUnit = "Kilometer"
                        oConversionFactor = 1000.0
                        convertUnit()
                    })

                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Result - $outputValue $outputUnit", style=MaterialTheme.typography.labelLarge)
    }
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview(){
    UnitConverter();
}

