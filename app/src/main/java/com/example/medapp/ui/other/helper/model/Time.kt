package com.example.medapp.ui.other.helper.model

data class Time(
	val minute: Double
) {
	init {
		val second = minute * 60
	}
}