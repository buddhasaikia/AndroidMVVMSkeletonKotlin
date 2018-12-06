package com.androidskeleton.mvvm.di.module

import android.arch.lifecycle.ViewModelProvider
import android.content.Context

import com.androidskeleton.mvvm.BuildConfig
import com.androidskeleton.mvvm.data.api.ApiService
import com.androidskeleton.mvvm.data.datasource.LocalDataSource
import com.androidskeleton.mvvm.data.datasource.RemoteDataSource
import com.androidskeleton.mvvm.data.repository.Repository
import com.androidskeleton.mvvm.util.CustomViewModelFactory
import com.androidskeleton.mvvm.util.ErrorMessageFactory
import com.androidskeleton.mvvm.util.NullOnEmptyConverterFactory
import com.androidskeleton.mvvm.util.Utils
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

import java.util.concurrent.TimeUnit

import javax.inject.Named
import javax.inject.Singleton

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class RepositoryModule {
    @Singleton
    @Provides
    internal fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    @Singleton
    @Provides
    internal fun provideStethoInterceptor(): StethoInterceptor {
        return StethoInterceptor()
    }

    @Singleton
    @Provides
    internal fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    @Named("ok-1")
    internal fun provideOkHttpClient1(loggingInterceptor: HttpLoggingInterceptor,
                                      stethoInterceptor: StethoInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addNetworkInterceptor(stethoInterceptor)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build()
    }

    @Singleton
    @Provides
    @Named("ok-2")
    internal fun provideOkHttpClient2(loggingInterceptor: HttpLoggingInterceptor,
                                      stethoInterceptor: StethoInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addNetworkInterceptor(stethoInterceptor)
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build()
    }


    @Singleton
    @Provides
    internal fun provideRxJavaCallAdapterFactory(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()
    }

    @Singleton
    @Provides
    internal fun provideRetrofit(@Named("ok-1") okHttpClient: OkHttpClient,
                                 converterFactory: GsonConverterFactory,
                                 adapterFactory: RxJava2CallAdapterFactory): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BuildConfig.URL_HOST_API)
                .addConverterFactory(NullOnEmptyConverterFactory())
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(adapterFactory)
                .client(okHttpClient)
                .build()
    }


    @Singleton
    @Provides
    internal fun provideErrorMessageFactory(): ErrorMessageFactory {
        return ErrorMessageFactory()
    }

    @Singleton
    @Provides
    internal fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    internal fun provideLocalDataSource(): LocalDataSource {
        return LocalDataSource()
    }

    @Singleton
    @Provides
    internal fun provideRemoteDataSource(apiService: ApiService): RemoteDataSource {
        return RemoteDataSource(apiService)
    }

    @Singleton
    @Provides
    internal fun provideRepository(localDatasource: LocalDataSource, remoteDatasource: RemoteDataSource): Repository {
        return Repository(remoteDatasource, localDatasource)
    }

    @Singleton
    @Provides
    internal fun provideViewModelFactory(repository: Repository): ViewModelProvider.Factory {
        return CustomViewModelFactory(repository)
    }

    @Singleton
    @Provides
    internal fun provideUtils(context: Context): Utils {
        return Utils(context)
    }
}
