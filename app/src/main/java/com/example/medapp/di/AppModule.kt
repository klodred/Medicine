package com.example.medapp.di

import android.content.Context
import com.example.medapp.components.AppContext
import com.example.medapp.ui.other.resources.ResourceProvider
import com.example.medapp.ui.other.resources.ResourceProviderImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val application: AppContext) {

	@Provides
	@Singleton
	fun provideContext(): Context {
		return application
	}

	@Provides
	@Singleton
	fun provideResourceProvider(resourceProviderImpl: ResourceProviderImpl): ResourceProvider {
		return resourceProviderImpl
	}

}