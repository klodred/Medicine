package com.example.medapp.ui.fragment.cylindricaldiffuser

import androidx.lifecycle.ViewModel
import com.example.medapp.di.viewmodels.ViewModelKey
import com.example.medapp.ui.fragment.enddiffuser.EndDiffuserFragment
import com.example.medapp.ui.fragment.enddiffuser.EndDiffuserViewModel
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.multibindings.IntoMap

@Subcomponent(modules = [CylindricalDiffuserModule::class, CylindricalDiffuserViewModelModule::class])
interface CylindricalDiffuserComponent {

	fun inject(fragment: CylindricalDiffuserFragment)
}

@Module
class CylindricalDiffuserModule()

@Module
abstract class CylindricalDiffuserViewModelModule {
	@Binds
	@IntoMap
	@ViewModelKey(CylindricalDiffuserViewModel::class)
	internal abstract fun viewModel(viewModel: CylindricalDiffuserViewModel): ViewModel
}