package com.example.medapp.ui.fragment.enddiffuser

import androidx.lifecycle.LiveData
import com.example.medapp.ui.fragment.enddiffuser.model.EndDiffuserInputValueType
import com.example.medapp.ui.model.CalculatedResult
import com.example.medapp.ui.other.helper.model.Time

interface EndDiffuserViewModelInterface {
	val enabledButton: LiveData<Boolean>
	val result: LiveData<CalculatedResult>
	val clear: LiveData<Boolean>

	fun changeInputValue(type: EndDiffuserInputValueType, value: Double?)

	fun calculate()

	fun clear()
}