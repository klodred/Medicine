package com.example.medapp.ui.activity.main

import androidx.lifecycle.ViewModel
import com.example.medapp.di.viewmodels.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import dagger.multibindings.IntoMap

@Subcomponent(modules = [MainScreenModule::class, MainScreenViewModelModule::class])
interface MainScreenComponent {

	fun inject(activity: MainActivity)

}

@Module
class MainScreenModule()

@Module
abstract class MainScreenViewModelModule {
	@Binds
	@IntoMap
	@ViewModelKey(MainViewModel::class)
	internal abstract fun viewModel(viewModel: MainViewModel): ViewModel
}