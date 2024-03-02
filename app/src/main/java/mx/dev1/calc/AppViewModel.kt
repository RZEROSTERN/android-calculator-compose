package mx.dev1.calc

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AppViewModel : ViewModel() {
    private val firstNumberMutable: MutableStateFlow<Double> = MutableStateFlow(0.0)
    val firstNumber = firstNumberMutable.asStateFlow()

    private val secondNumberMutable: MutableStateFlow<Double> = MutableStateFlow(0.0)
    val secondNumber = secondNumberMutable.asStateFlow()
}