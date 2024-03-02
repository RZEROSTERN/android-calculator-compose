package mx.dev1.calc.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mx.dev1.calc.models.CalculatorButton
import mx.dev1.calc.ui.theme.Cyan
import mx.dev1.calc.ui.theme.Red
import mx.dev1.calc.utils.CalculatorButtonType

@Composable
fun CalcButton(button: CalculatorButton, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.secondary)
            .fillMaxHeight()
            .aspectRatio(1f),
        contentAlignment = Alignment.Center,
    ) {
        val contentColor = when(button.type) {
            CalculatorButtonType.NORMAL -> Color.White
            CalculatorButtonType.ACTION -> Red
            else -> Cyan
        }

        if(button.text != null) {
            Text(
                button.text,
                color = contentColor,
                fontWeight = FontWeight.Bold,
                fontSize =
                if (button.type == CalculatorButtonType.ACTION)
                    24.sp
                else
                    16.sp

            )
        } else {
            Icon(
                modifier = Modifier.size(32.dp),
                imageVector = button.icon ?: Icons.Outlined.Warning,
                contentDescription = null,
                tint = contentColor,
            )
        }
    }
}