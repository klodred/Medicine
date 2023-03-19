package com.example.medapp.ui.other.resources

import android.content.Context
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import javax.inject.Inject

class ResourceProviderImpl @Inject constructor(private val context: Context) : ResourceProvider {

	override fun getString(stringResId: Int): String {
		return context.getString(stringResId)
	}

	override fun getString(stringResId: Int, vararg formatArgs: Any): String {
		return context.getString(stringResId, *formatArgs)
	}

	@ColorInt
	override fun getColor(@ColorRes colorResId: Int): Int {
		return ContextCompat.getColor(context, colorResId)
	}

}