package com.example.medapp.ui.fragment.cylindricaldiffuser

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.medapp.ui.fragment.cylindricaldiffuser.model.CylindricalDiffuserInputType
import com.example.medapp.ui.other.helper.CalculateHelper
import com.example.medapp.ui.other.helper.model.CylindricalDiffuserParams
import com.example.medapp.ui.other.helper.model.Time
import javax.inject.Inject

// Цилиндрический диффузор

class CylindricalDiffuserViewModel @Inject constructor(

) : ViewModel(), CylindricalDiffuserViewModelInterface {


	private val _enabledButton = MutableLiveData<Boolean>()
	override val enabledButton: LiveData<Boolean> = _enabledButton

	private val _result = MutableLiveData<Time>()
	override val result: LiveData<Time> = _result

	private val _clear = MutableLiveData<Boolean>()
	override val clear: LiveData<Boolean> = _clear

	private var length: Double? = null
	private var laserPower: Double? = null
	private var distance: Double? = null
	private var treatmentDose: Double? = null
	private var energyLoss: Double? = null


	init {
		setButtonEnabled()
	}

	override fun changeInputValue(type: CylindricalDiffuserInputType, value: Double?) {
		when(type) {
			CylindricalDiffuserInputType.LENGTH -> {
				length = value
			}

			CylindricalDiffuserInputType.LASER_POWER -> {
				laserPower = value
			}

			CylindricalDiffuserInputType.DISTANCE -> {
				distance = value
			}

			CylindricalDiffuserInputType.TREATMENT_DOSE -> {
				treatmentDose = value
			}

			CylindricalDiffuserInputType.ENERGY_LOSS -> {
				energyLoss = value
			}
		}

		setButtonEnabled()
	}

	override fun calculate() {
		_result.value = CalculateHelper.calculateCylindricalDiffuser(
			CylindricalDiffuserParams(
				length = length ?: return,
				laserPower = laserPower ?: return,
				distance = distance ?: return,
				treatmentDose = treatmentDose ?: return,
				energyLoss = energyLoss ?: return
			)
		)
	}

	override fun clear() {
		_clear.value = true
	}

	private fun setButtonEnabled() {
		val values = listOf(length, laserPower, distance, treatmentDose, energyLoss)

		_enabledButton.value = !values.contains(null)
	}
}