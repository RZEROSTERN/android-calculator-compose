package mx.dev1.calc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import mx.dev1.calc.components.CalcButton
import mx.dev1.calc.models.CalculatorButton
import mx.dev1.calc.ui.theme.CalcTheme
import mx.dev1.calc.utils.CalculatorButtonType

class MainActivity : ComponentActivity() {
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
                            CalculatorButton("AC", CalculatorButtonType.RESET),
                            CalculatorButton("AC", CalculatorButtonType.RESET),
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
                                type = CalculatorButtonType.NORMAL
                            ),

                            CalculatorButton("0", CalculatorButtonType.NORMAL),
                            CalculatorButton(".", CalculatorButtonType.NORMAL),
                            CalculatorButton("=", CalculatorButtonType.ACTION),

                        )
                    }

                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.BottomCenter
                    ) {
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

                                    }
                                )
                            }
                        }
                    }
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.TopCenter
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier
                                .padding(horizontal = 8.dp, vertical = 4.dp)
                                .clip(
                                    RoundedCornerShape(8.dp)
                                )
                        ) {
                            Icon(
                                modifier = Modifier.size(20.dp),
                                painter = painterResource(id = R.drawable.ic_light_mode),
                                contentDescription = null,
                                tint = Color.White
                            )
                            Icon(
                                modifier = Modifier.size(20.dp),
                                painter = painterResource(id = R.drawable.ic_dark_mode),
                                contentDescription = null,
                                tint = Color.White
                            )
                        }
                    }
                }
            }
        }
    }
}




