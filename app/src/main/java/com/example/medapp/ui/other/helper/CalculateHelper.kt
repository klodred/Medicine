package com.example.medapp.ui.other.helper

import android.util.Log
import com.example.medapp.ui.other.helper.model.CylindricalDiffuserParams
import com.example.medapp.ui.other.helper.model.EndDiffuserParams
import com.example.medapp.ui.other.helper.model.SphericalDiffuserParams
import com.example.medapp.ui.other.helper.model.Time
import kotlin.math.pow

object CalculateHelper {

	fun calculateEndDiffuser(data: EndDiffuserParams): Time {
		val S = calculateS(data)
		val L = calculateL(data)
		val T = (1000 * data.treatmentDose * S) / (data.laserPower * L)
		return Time(second = T)
	}

	fun calculateSphericalDiffuser(data: SphericalDiffuserParams): Time {
		val S = calculateS(data)
		val L = calculateL(data)
		val result = ((1000 * data.treatmentDose * S) / data.laserPower) / L
		return Time(second = result)
	}

	fun calculateCylindricalDiffuser(data: CylindricalDiffuserParams): Time {
		val nw = calculateNw(data)
		val L = calculateL(data)
		val result = (data.length * 10472 * data.distance * nw) / (data.laserPower * L)
		return Time(minute = result)
	}

	// calculate values for end diffuser

	private fun calculateS(data: EndDiffuserParams): Double {
		return 3.14 * data.diameterLightSpot * data.diameterLightSpot / 4
	}

	private fun calculateL(data: EndDiffuserParams): Double {
		return (100 - data.energyLoss) / 100
	}

	// calculate values for spherical diffuser

	private fun calculateD(data: SphericalDiffuserParams): Double {
		return cbrt(6 * data.bubbleVolume / 3.14)
	}

	private fun calculateS(data: SphericalDiffuserParams): Double {
		val d = calculateD(data)
		return 3.14 * d * d
	}

	private fun calculateL(data: SphericalDiffuserParams): Double {
		return (100 - data.energyLoss) / 100
	}

	// calculate values for cylindrical diffuser

	private fun calculateNw(data: CylindricalDiffuserParams): Double {
		return data.treatmentDose / 100
	}

	private fun calculateKp(data: CylindricalDiffuserParams): Double {
		return 1.0
	}

	private fun calculateL(data: CylindricalDiffuserParams): Double {
		return (100 - data.energyLoss) / 100
	}

	private fun cbrt(a: Double): Double {
		return a.pow(1 / 3.toDouble())
	}
}