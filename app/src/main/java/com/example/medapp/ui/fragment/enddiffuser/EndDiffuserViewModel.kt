package com.example.medapp.ui.fragment.enddiffuser

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.medapp.ui.fragment.enddiffuser.model.EndDiffuserInputValueType
import javax.inject.Inject

// Торцевой диффузор

class EndDiffuserViewModel @Inject constructor(

) : ViewModel(), EndDiffuserViewModelInterface {

	private val _enabledButton = MutableLiveData<Boolean>()
	override val enabledButton: LiveData<Boolean> = _enabledButton

	private var diameterLightSpot: Double? = null
	private var laserPower: Double? = null
	private var treatmentDose: Double? = null
	private var energyLoss: Double? = null


	init {
		setButtonEnabled()
	}

	override fun changeInputValue(type: EndDiffuserInputValueType, value: Double?) {
		when(type) {
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

	}

	private fun setButtonEnabled() {
		val values = listOf(diameterLightSpot, laserPower, treatmentDose, energyLoss)

		_enabledButton.value = !values.contains(null)
	}
}