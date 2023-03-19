package com.example.medapp.di

import com.example.medapp.components.AppContext
import com.example.medapp.ui.activity.main.MainScreenComponent
import com.example.medapp.ui.activity.main.MainScreenModule
import com.example.medapp.ui.fragment.MenuScreenComponent
import com.example.medapp.ui.fragment.MenuScreenModule
import com.example.medapp.ui.fragment.cylindricaldiffuser.CylindricalDiffuserComponent
import com.example.medapp.ui.fragment.cylindricaldiffuser.CylindricalDiffuserModule
import com.example.medapp.ui.fragment.enddiffuser.EndDiffuserComponent
import com.example.medapp.ui.fragment.enddiffuser.EndDiffuserModule
import com.example.medapp.ui.fragment.sphericaldiffuser.SphericalDiffuserComponent
import com.example.medapp.ui.fragment.sphericaldiffuser.SphericalDiffuserModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
	modules = [
		AppModule::class,
		ViewModelModule::class
	]
)
interface AppComponent {

	fun inject(appContext: AppContext)

	fun plus(mainScreenModule: MainScreenModule): MainScreenComponent

	fun plus(menuScreenModule: MenuScreenModule): MenuScreenComponent

	fun plus(endDiffuserModule: EndDiffuserModule): EndDiffuserComponent

	fun plus(sphericalDiffuserModule: SphericalDiffuserModule): SphericalDiffuserComponent

	fun plus(cylindricalDiffuserModule: CylindricalDiffuserModule): CylindricalDiffuserComponent
}