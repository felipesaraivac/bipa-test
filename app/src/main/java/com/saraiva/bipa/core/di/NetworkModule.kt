package com.saraiva.bipa.core.di

import com.google.gson.GsonBuilder
import com.saraiva.bipa.core.Constants
import com.saraiva.bipa.core.network.BipaService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import com.saraiva.bipa.data.RepositoryImpl
import com.saraiva.bipa.domain.repository.Repository

@Module
@InstallIn(SingletonComponent::class)
object DI {

    @Singleton
    @Provides
    fun provideService(): BipaService {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
        return retrofit.create(BipaService::class.java)
    }

    @Singleton
    @Provides
    fun provideRepository(service: BipaService): Repository = RepositoryImpl(service)

}