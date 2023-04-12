package com.example.medapp.ui.fragment.sphericaldiffuser

import androidx.lifecycle.LiveData
import com.example.medapp.ui.fragment.enddiffuser.model.EndDiffuserInputValueType
import com.example.medapp.ui.fragment.sphericaldiffuser.model.CalculatedResult
import com.example.medapp.ui.fragment.sphericaldiffuser.model.SphericalDiffuserValueType
import com.example.medapp.ui.other.helper.model.Time

interface SphericalDiffuserViewModelInterface {
	val enabledButton: LiveData<Boolean>
	val result: LiveData<CalculatedResult>
	val clear: LiveData<Boolean>

	fun changeInputValue(type: SphericalDiffuserValueType, value: Double?)

	fun calculate()

	fun clear()
}