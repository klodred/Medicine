package com.example.medapp.ui.fragment.sphericaldiffuser

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.medapp.ui.fragment.enddiffuser.model.EndDiffuserInputValueType
import com.example.medapp.ui.fragment.sphericaldiffuser.model.CalculatedResult
import com.example.medapp.ui.fragment.sphericaldiffuser.model.SphericalDiffuserValueType
import com.example.medapp.ui.other.helper.CalculateHelper
import com.example.medapp.ui.other.helper.model.EndDiffuserParams
import com.example.medapp.ui.other.helper.model.SphericalDiffuserParams
import com.example.medapp.ui.other.helper.model.Time
import javax.inject.Inject

// Сферический диффузор

class SphericalDiffuserViewModel @Inject constructor(

) : ViewModel(), SphericalDiffuserViewModelInterface {


	private val _enabledButton = MutableLiveData<Boolean>()
	override val enabledButton: LiveData<Boolean> = _enabledButton

	private val _result = MutableLiveData<CalculatedResult>()
	override val result: LiveData<CalculatedResult> = _result

	private val _clear = MutableLiveData<Boolean>()
	override val clear: LiveData<Boolean> = _clear

	private var bubbleVolume: Double? = null
	private var laserPower: Double? = null
	private var treatmentDose: Double? = null
	private var energyLoss: Double? = 0.0


	init {
		setButtonEnabled()
	}

	override fun changeInputValue(type: SphericalDiffuserValueType, value: Double?) {
		when (type) {
			SphericalDiffuserValueType.BUBBLE_VOLUME -> {
				bubbleVolume = value
			}

			SphericalDiffuserValueType.LASER_POWER -> {
				laserPower = value
			}

			SphericalDiffuserValueType.TREATMENT_DOSE -> {
				treatmentDose = value
			}

			SphericalDiffuserValueType.ENERGY_LOSS -> {
				energyLoss = value
			}
		}

		setButtonEnabled()
	}

	override fun calculate() {
		val data = SphericalDiffuserParams(
			bubbleVolume = bubbleVolume ?: return,
			laserPower = laserPower ?: return,
			treatmentDose = treatmentDose ?: return,
			energyLoss = energyLoss ?: return
		)
		_result.value = CalculatedResult(
			time = CalculateHelper.calculateSphericalDiffuser(data),
			S = CalculateHelper.calculateS(data)
		)
	}

	override fun clear() {
		_clear.value = true
	}

	private fun setButtonEnabled() {
		val values = listOf(bubbleVolume, laserPower, treatmentDose, energyLoss)

		_enabledButton.value = !values.contains(null)
	}
}