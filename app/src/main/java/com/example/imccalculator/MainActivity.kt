package com.example.imccalculator

import android.graphics.Color.rgb
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.imccalculator.ui.theme.ImcCalculatorTheme
import kotlin.math.pow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ImcCalculatorTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CalculadoraImcScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun CalculadoraImcScreen(modifier: Modifier = Modifier) {

    Column(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
                .background(color = colorResource(R.color.usedClassColor)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.bmi),
                contentDescription = "IMC logo",
                modifier = Modifier
                    .size(85.dp)
                    .padding(vertical = 16.dp)
            )
            Text(
                text = "Calculadora de IMC",
                fontSize = 24.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }


        var altura by remember {
            mutableStateOf("")
        }
        var peso by remember {
            mutableStateOf("")
        }
        var imc by remember {
            mutableStateOf(0.0)
        }

        var categoria = ""
        var colorCard = Color(7, 166, 213)

        when {
            imc < 18.5 ->   {
                categoria = "Abaixo do peso"
                colorCard = Color.Red
            }
            imc > 18.5 && imc < 25 -> {
                categoria = "Peso ideal"
                colorCard = Color.Green
            }
            imc in 25.0..<30.0 -> {
                categoria = "Levemente acima do peso"
                colorCard = Color(255, 165, 0)
            }
            imc in 30.0..35.0 -> {
                categoria = "Obesidade grau I"
                colorCard = Color.Red
            }
            imc in 35.0..40.0 -> {
                categoria = "Obesidade grau II"
                colorCard = Color.Red
            }
            else -> {
                categoria = "Obesidade grau II"
                colorCard = Color.Red
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .offset(y = (-30).dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFF9F6F6)
                ),
                elevation = CardDefaults.cardElevation(4.dp),

            ) {

                Column(
                    modifier = Modifier.fillMaxSize(),
                    Arrangement.spacedBy(24.dp),
                    Alignment.CenterHorizontally
                ) {
                    Text("Seus Dados")
                    TextField(
                        value = altura,
                        onValueChange = {
                            altura = it
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        label = {
                            Text("Altura")
                        }
                    )
                    TextField(
                        value = peso,
                        onValueChange = {
                            peso = it
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        label = {
                            Text("Peso")
                        }
                    )

                    Button(
                        onClick = {
                            imc = calcularImc(peso, altura)
                        }
                    ) {
                        Text("CALCULAR")
                    }
                }



            }
            Card(modifier = Modifier.height(80.dp)
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
                colors = CardDefaults.cardColors(
                    containerColor = colorCard
                )) {
                Row(modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center) {
                    Text("$imc",
                        fontSize = 20.sp,
                        color = Color.White)
                    Spacer(modifier = Modifier.width(20.dp))
                    Text(categoria,
                        fontSize = 20.sp,
                        color = Color.White)
                }
            }
        }

    }
}
