package com.example.medapp.ui.fragment.sphericaldiffuser

import androidx.lifecycle.ViewModel
import com.example.medapp.di.viewmodels.ViewModelKey
import com.example.medapp.ui.fragment.enddiffuser.EndDiffuserFragment
import com.example.medapp.ui.fragment.enddiffuser.EndDiffuserViewModel
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.multibindings.IntoMap

@Subcomponent(modules = [SphericalDiffuserModule::class, SphericalDiffuserViewModelModule::class])
interface SphericalDiffuserComponent {

	fun inject(fragment: SphericalDiffuserFragment)
}

@Module
class SphericalDiffuserModule()

@Module
abstract class SphericalDiffuserViewModelModule {
	@Binds
	@IntoMap
	@ViewModelKey(SphericalDiffuserViewModel::class)
	internal abstract fun viewModel(viewModel: SphericalDiffuserViewModel): ViewModel
}