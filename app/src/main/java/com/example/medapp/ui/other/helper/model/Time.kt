package com.example.medapp.ui.other.helper.model

data class Time(
	var minute: Double? = null,
	var second: Double? = null
) {
	init {
		minute?.let {
			second = it * 60
		}
		second?.let {
			minute = it / 60
		}
	}
}