package mx.dev1.calc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mx.dev1.calc.components.CalcButton
import mx.dev1.calc.models.CalculatorButton
import mx.dev1.calc.ui.theme.CalcTheme
import mx.dev1.calc.utils.CalculatorButtonType

class MainActivity : ComponentActivity() {
    private val viewModel: AppViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalcTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.secondary
                ) {
                    val calculatorButtons = remember {
                        mutableStateListOf(
                            CalculatorButton("AC", CalculatorButtonType.RESET),
                            CalculatorButton("+/-", CalculatorButtonType.RESET),
                            CalculatorButton("%", CalculatorButtonType.ACTION),
                            CalculatorButton("/", CalculatorButtonType.ACTION),

                            CalculatorButton("7", CalculatorButtonType.NORMAL),
                            CalculatorButton("8", CalculatorButtonType.NORMAL),
                            CalculatorButton("9", CalculatorButtonType.NORMAL),
                            CalculatorButton("x", CalculatorButtonType.ACTION),

                            CalculatorButton("4", CalculatorButtonType.NORMAL),
                            CalculatorButton("5", CalculatorButtonType.NORMAL),
                            CalculatorButton("6", CalculatorButtonType.NORMAL),
                            CalculatorButton("-", CalculatorButtonType.ACTION),

                            CalculatorButton("1", CalculatorButtonType.NORMAL),
                            CalculatorButton("2", CalculatorButtonType.NORMAL),
                            CalculatorButton("3", CalculatorButtonType.NORMAL),
                            CalculatorButton("+", CalculatorButtonType.ACTION),

                            CalculatorButton(
                                icon = Icons.Outlined.Refresh,
                                type = CalculatorButtonType.RESET
                            ),

                            CalculatorButton("0", CalculatorButtonType.NORMAL),
                            CalculatorButton(".", CalculatorButtonType.NORMAL),
                            CalculatorButton("=", CalculatorButtonType.ACTION),

                        )
                    }
                    val (uiText, setUiText) = remember {
                        mutableStateOf("0")
                    }
                    val (input, setInput) = remember {
                        mutableStateOf<String?>(null)
                    }

                    LaunchedEffect(uiText) {
                        if(uiText.startsWith("0") && uiText != "0")
                            setUiText(uiText.substring(1))
                    }

                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.BottomCenter
                    ) {
                        Column {
                            Text(
                                modifier = Modifier.padding(horizontal = 24.dp).align(Alignment.End),
                                text = uiText,
                                fontSize = 48.sp,
                                fontWeight = FontWeight.Bold,
                                color = if(isSystemInDarkTheme()) Color.White else Color.Gray
                            )
                            Spacer(
                                modifier = Modifier.height(32.dp)
                            )
                            LazyVerticalGrid(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                                    .background(MaterialTheme.colorScheme.primary)
                                    .padding(8.dp),
                                columns = GridCells.Fixed(4),
                                verticalArrangement = Arrangement.spacedBy(16.dp),
                                horizontalArrangement = Arrangement.spacedBy(16.dp),
                                contentPadding = PaddingValues(16.dp)
                            ) {
                                items(calculatorButtons) {
                                    CalcButton(button = it,
                                        onClick = {
                                            when(it.type) {
                                                CalculatorButtonType.NORMAL -> {
                                                    runCatching {
                                                        setUiText(
                                                            uiText.toInt().toString() + it.text
                                                        )
                                                    }.onFailure { throwable ->
                                                        setUiText(uiText + it.text)
                                                    }

                                                    setInput((input ?: "") + it.text)

                                                    if(viewModel.action.value.isNotEmpty()) {
                                                        if(viewModel.secondNumber.value == null) {
                                                            viewModel.setSecondNumber(
                                                                it.text!!.toDouble()
                                                            )
                                                        } else {
                                                            if(viewModel.secondNumber.value
                                                                .toString()
                                                                .split(".")[1] == "0") {
                                                                viewModel.setSecondNumber(
                                                                    (viewModel.secondNumber.value
                                                                        .toString()
                                                                        .split(".")
                                                                        .first() + it.text!!)
                                                                        .toDouble()
                                                                )
                                                            } else {
                                                                viewModel.setSecondNumber(
                                                                    (viewModel.secondNumber.value
                                                                        .toString() + it.text!!)
                                                                        .toDouble()
                                                                )
                                                            }
                                                        }
                                                    }
                                                }
                                                CalculatorButtonType.ACTION -> {
                                                    if(it.text == "=") {
                                                        val result = viewModel.getResult()
                                                        setUiText(result.toString())
                                                        setInput(null)
                                                        viewModel.resetAll()
                                                    } else {
                                                        runCatching {
                                                            setUiText(
                                                                uiText.toInt().toString() + it.text
                                                            )
                                                        }.onFailure { throwable ->
                                                            setUiText(uiText + it.text)
                                                        }

                                                        if(input != null) {
                                                            if(viewModel.firstNumber.value == null) {
                                                                viewModel.setFirstNumber(
                                                                    input.toDouble()
                                                                )
                                                            } else {
                                                                viewModel.setSecondNumber(
                                                                    input.toDouble()
                                                                )
                                                            }
                                                            viewModel.setAction(it.text!!)
                                                            setInput(null)
                                                        }
                                                    }
                                                }
                                                CalculatorButtonType.RESET -> {
                                                    setUiText("0")
                                                    setInput(null)
                                                    viewModel.resetAll()
                                                }
                                            }
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
