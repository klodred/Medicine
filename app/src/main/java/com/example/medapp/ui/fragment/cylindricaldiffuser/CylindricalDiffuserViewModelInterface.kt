package com.example.medapp.ui.fragment.cylindricaldiffuser

import androidx.lifecycle.LiveData
import com.example.medapp.ui.fragment.cylindricaldiffuser.model.CylindricalDiffuserInputType
import com.example.medapp.ui.other.helper.model.Time

interface CylindricalDiffuserViewModelInterface {
	val enabledButton: LiveData<Boolean>
	val result: LiveData<Time>
	val clear: LiveData<Boolean>

	fun changeInputValue(type: CylindricalDiffuserInputType, value: Double?)

	fun calculate()

	fun clear()
}