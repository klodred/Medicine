package com.example.medapp.ui.fragment.enddiffuser

import androidx.lifecycle.LiveData
import com.example.medapp.ui.fragment.enddiffuser.model.EndDiffuserInputValueType

interface EndDiffuserViewModelInterface {
	val enabledButton: LiveData<Boolean>

	fun changeInputValue(type: EndDiffuserInputValueType, value: Double?)

	fun calculate()
}