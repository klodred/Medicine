package com.example.medapp.ui.fragment.enddiffuser

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.medapp.ui.fragment.enddiffuser.model.EndDiffuserInputValueType
import com.example.medapp.ui.model.CalculatedResult
import com.example.medapp.ui.other.helper.CalculateHelper
import com.example.medapp.ui.other.helper.model.EndDiffuserParams
import com.example.medapp.ui.other.helper.model.Time
import javax.inject.Inject

// Торцевой диффузор

class EndDiffuserViewModel @Inject constructor(

) : ViewModel(), EndDiffuserViewModelInterface {

	private val _enabledButton = MutableLiveData<Boolean>()
	override val enabledButton: LiveData<Boolean> = _enabledButton

	private val _result = MutableLiveData<CalculatedResult>()
	override val result: LiveData<CalculatedResult> = _result

	private val _clear = MutableLiveData<Boolean>()
	override val clear: LiveData<Boolean> = _clear

	private var diameterLightSpot: Double? = null
	private var laserPower: Double? = null
	private var treatmentDose: Double? = null
	private var energyLoss: Double? = 0.0


	init {
		setButtonEnabled()
	}

	override fun changeInputValue(type: EndDiffuserInputValueType, value: Double?) {
		when (type) {
			EndDiffuserInputValueType.DIAMETER_LIGHT_SPOT -> {
				diameterLightSpot = value
			}

			EndDiffuserInputValueType.LASER_POWER -> {
				laserPower = value
			}

			EndDiffuserInputValueType.TREATMENT_DOSE -> {
				treatmentDose = value
			}

			EndDiffuserInputValueType.ENERGY_LOSS -> {
				energyLoss = value
			}
		}

		setButtonEnabled()
	}

	override fun calculate() {
		val data = EndDiffuserParams(
			diameterLightSpot = diameterLightSpot ?: return,
			laserPower = laserPower ?: return,
			treatmentDose = treatmentDose ?: return,
			energyLoss = energyLoss ?: return
		)

		_result.value = CalculatedResult(
			time = CalculateHelper.calculateEndDiffuser(data),
			vtOnSm = CalculateHelper.calculateVtOnSm(data)
		)
	}

	override fun clear() {
		_clear.value = true
	}

	private fun setButtonEnabled() {
		val values = listOf(diameterLightSpot, laserPower, treatmentDose, energyLoss)

		_enabledButton.value = !values.contains(null)
	}
}