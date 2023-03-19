package com.example.medapp.di

import androidx.lifecycle.ViewModelProvider
import com.example.medapp.di.viewmodels.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {

	@Binds
	internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}