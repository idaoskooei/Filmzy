package com.myapp.myapplication.di

import android.content.Context
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.DialogNavigator
import com.myapp.myapplication.API_TOKEN
import com.myapp.myapplication.BASE_URL
import com.myapp.myapplication.repo.category.CategoryRemoteService
import com.myapp.myapplication.repo.search.SearchRemoteService
import com.myapp.myapplication.ui.navigation.NavigationManager
import com.myapp.myapplication.ui.navigation.NavigationManagerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideNavigationManager(): NavigationManager = NavigationManagerImpl()

    @Provides
    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @Singleton
    fun provideNavController(@ApplicationContext context: Context) = NavHostController(context).apply {
        navigatorProvider.addNavigator(ComposeNavigator())
        navigatorProvider.addNavigator(DialogNavigator())
    }
    @Provides
    @Singleton
    fun provideSearchRemoteService(
        retrofit: Retrofit
    ): SearchRemoteService {
        return retrofit
            .create(SearchRemoteService::class.java)
    }

    @Provides
    @Singleton
    fun provideCategoryRemoteService(
        retrofit: Retrofit
    ): CategoryRemoteService {
        return retrofit
            .create(CategoryRemoteService::class.java)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addNetworkInterceptor(Interceptor { chain ->
                val request: Request = chain.request()
                val newRequest: Request = request.newBuilder()
                    .addHeader("Authorization", API_TOKEN)
                    .build()
                chain.proceed(newRequest)
            })
            .addInterceptor(HttpLoggingInterceptor().apply {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            })
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}