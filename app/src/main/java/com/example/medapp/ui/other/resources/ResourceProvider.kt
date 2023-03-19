package com.example.medapp.ui.other.resources

import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.PluralsRes
import androidx.annotation.StringRes

interface ResourceProvider {

	fun getString(@StringRes stringResId: Int): String

	fun getString(@StringRes stringResId: Int, vararg formatArgs: Any): String

	@ColorInt
	fun getColor(@ColorRes colorResId: Int): Int

}