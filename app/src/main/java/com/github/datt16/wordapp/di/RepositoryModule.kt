package com.github.datt16.wordapp.di

import com.github.datt16.wordapp.repository.WordAppRepository
import com.github.datt16.wordapp.repository.WordAppRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @ViewModelScoped
    @Singleton
    abstract fun bindWordAppRepository(impl: WordAppRepositoryImpl): WordAppRepository

}