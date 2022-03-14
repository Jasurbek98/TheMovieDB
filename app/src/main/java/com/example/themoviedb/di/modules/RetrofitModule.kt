package com.example.themoviedb.di.modules

import android.content.Context
import com.example.themoviedb.app.App
import com.example.themoviedb.data.local.LocalStorage
import com.example.themoviedb.utils.API_KEY
import com.example.themoviedb.utils.BASE_URL
import com.example.themoviedb.utils.isConnected
import com.readystatesoftware.chuck.ChuckInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Provider
import javax.inject.Singleton

/**
 * Created by Jasurbek Kurganbayev 14/03/2022
 */


private const val MAX_STALE = 60 * 60 * 24 * 30 // 30day

private const val cacheSize: Long = 30 * 1024 * 1024 // 30MB
private val cache = Cache(App.instance.cacheDir, cacheSize)

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

    @Provides
    @Singleton
    fun getApi(): String = BASE_URL

    @Provides
    @Singleton
    @Named("course_base_url")
    fun getCourseApi(): String = "http://cbu.uz"

    @Provides
    @Singleton
    fun getLogging(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    @Provides
    @Singleton
    fun okHttpClient(
        logging: HttpLoggingInterceptor,
        @ApplicationContext context: Context,
        storage: LocalStorage,
//        authApi: Provider<AuthApi>
    ): OkHttpClient = OkHttpClient.Builder()
//        .authenticator(AuthInterceptor(authApi, storage))
        .addInterceptor(logging)
        .cache(cache)
        .addInterceptor(provideOfflineCacheInterceptor())
        .addInterceptor(ChuckInterceptor(context))//for seeing responses and requests from emulator
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
            val originalHttpUrl = chain.request().url
            val url = originalHttpUrl.newBuilder().addQueryParameter("api_key", API_KEY).build()
            request.url(url)
            val response = chain.proceed(request.build())
            return@addInterceptor response
        }
        .build()

    @Provides
    @Singleton
    fun getRetrofit(
        api: String,
        client: OkHttpClient
    ): Retrofit = Retrofit.Builder()
        .baseUrl(api)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()


    //retrieves max-stale cache if connection is off
    private fun provideOfflineCacheInterceptor() = Interceptor { chain ->
        var request = chain.request()

        if (!isConnected()) {
            val cacheControl = CacheControl.Builder()
                .maxStale(MAX_STALE, TimeUnit.SECONDS)
                .build()

            request = request.newBuilder()
                .removeHeader("Cache-Control")
                .cacheControl(cacheControl)
                .build()
        }
        chain.proceed(request)
    }
}