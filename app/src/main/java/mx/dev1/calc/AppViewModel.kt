package mx.dev1.calc

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AppViewModel : ViewModel() {
    private val firstNumberMutable: MutableStateFlow<Double?> = MutableStateFlow(null)
    val firstNumber = firstNumberMutable.asStateFlow()

    private val secondNumberMutable: MutableStateFlow<Double?> = MutableStateFlow(null)
    val secondNumber = secondNumberMutable.asStateFlow()

    private val actionMutable: MutableStateFlow<String> = MutableStateFlow("")
    val action = actionMutable.asStateFlow()

    fun setFirstNumber(input: Double) {
        firstNumberMutable.update { input }
    }

    fun setSecondNumber(input: Double) {
        secondNumberMutable.update { input }
    }

    fun setAction(action: String) {
        actionMutable.update { action }
    }

    fun getResult(): Double {
        return when(actionMutable.value) {
            "+" -> { firstNumberMutable.value!! + secondNumberMutable.value!! }
            "-" -> { firstNumberMutable.value!! - secondNumberMutable.value!! }
            "x" -> { firstNumberMutable.value!! * secondNumberMutable.value!! }
            "/" -> { firstNumberMutable.value!! / secondNumberMutable.value!! }
            "%" -> { firstNumberMutable.value!! * (secondNumberMutable.value!! / 100) }
            else -> 0.0
        }
    }

    fun resetAll() {
        actionMutable.update { "" }
        firstNumberMutable.update { null }
        secondNumberMutable.update { null }
    }
}