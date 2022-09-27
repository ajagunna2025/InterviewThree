package com.example.interviewthree.di

import com.example.interviewthree.model.remote.KuCoinService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun providesRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.kucoin.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun providesKuCoinService(retrofit: Retrofit): KuCoinService = retrofit.create()
}