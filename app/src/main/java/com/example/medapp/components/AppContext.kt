package com.example.medapp.components

import android.app.Application
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.example.medapp.di.AppComponent
import com.example.medapp.di.AppModule
import com.example.medapp.di.DaggerAppComponent
import javax.inject.Inject

class AppContext : Application(), LifecycleObserver {

	lateinit var appComponent: AppComponent
		private set

	companion object {
		lateinit var instance: AppContext
			private set
	}

	override fun onCreate() {
		super.onCreate()
		instance = this

		this.appComponent = initDaggerComponent()

		appComponent.inject(this)

	}

	private fun initDaggerComponent(): AppComponent {
		return DaggerAppComponent.builder().appModule(AppModule(this)).build()
	}


	//endregion

}