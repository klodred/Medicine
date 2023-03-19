package com.example.medapp.ui.activity.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import com.example.medapp.R
import com.example.medapp.components.AppContext
import com.example.medapp.ui.fragment.menu.MenuFragment
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

	@Inject
	lateinit var mViewModelFactory: ViewModelProvider.Factory

	lateinit var viewModel: MainViewModelInterface

	companion object {

	}

	override fun onCreate(savedInstanceState: Bundle?) {
		configureDI()
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
		openMenuScreen()
	}

	private fun configureDI() {
		val component = AppContext.instance.appComponent.plus(
			MainScreenModule()
		)
		component.inject(this)
		viewModel = ViewModelProvider(this, mViewModelFactory)[MainViewModel::class.java]
	}


	//region ==================== UI handlers ====================


	//endregion

	//region ===================== UI ======================


	//endregion

	//region ===================== Internal ======================

	private fun openMenuScreen() {
		val fragment = MenuFragment.newInstance()
		supportFragmentManager.beginTransaction().let {
			val currentContainer = R.id.contentContainer
			it.replace(currentContainer, fragment)
			it.commitNow()
		}
	}

	//endregion
}