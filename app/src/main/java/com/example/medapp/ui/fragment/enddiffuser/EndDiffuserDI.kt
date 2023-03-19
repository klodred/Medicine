package com.example.medapp.ui.fragment.enddiffuser

import androidx.lifecycle.ViewModel
import com.example.medapp.di.viewmodels.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.multibindings.IntoMap

@Subcomponent(modules = [EndDiffuserModule::class, EndDiffuserViewModelModule::class])
interface EndDiffuserComponent {

	fun inject(fragment: EndDiffuserFragment)
}

@Module
class EndDiffuserModule()

@Module
abstract class EndDiffuserViewModelModule {
	@Binds
	@IntoMap
	@ViewModelKey(EndDiffuserViewModel::class)
	internal abstract fun viewModel(viewModel: EndDiffuserViewModel): ViewModel
}