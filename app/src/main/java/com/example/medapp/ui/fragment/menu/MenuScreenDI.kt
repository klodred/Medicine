package com.example.medapp.ui.fragment

import androidx.lifecycle.ViewModel
import com.example.medapp.di.viewmodels.ViewModelKey
import com.example.medapp.ui.fragment.menu.MenuFragment
import com.example.medapp.ui.fragment.menu.MenuViewModel
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.multibindings.IntoMap

@Subcomponent(modules = [MenuScreenModule::class, MenuScreenViewModelModule::class])
interface MenuScreenComponent {

	fun inject(fragment: MenuFragment)

}

@Module
class MenuScreenModule()

@Module
abstract class MenuScreenViewModelModule {
	@Binds
	@IntoMap
	@ViewModelKey(MenuViewModel::class)
	internal abstract fun viewModel(viewModel: MenuViewModel): ViewModel
}