package mx.dev1.calc.models

import androidx.compose.ui.graphics.vector.ImageVector
import mx.dev1.calc.utils.CalculatorButtonType

data class CalculatorButton (
    val text: String? = null,
    val type: CalculatorButtonType,
    val icon: ImageVector? = null,
)