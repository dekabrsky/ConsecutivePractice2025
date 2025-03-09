package ru.dekabrsky.consecutivepractice2025.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import okhttp3.*
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.dekabrsky.consecutivepractice2025.listWithDetails.data.api.MovieApi


val networkModule = module {
    factory { provideRetrofit(get()) }
    single { provideNetworkApi(get()) }
}

fun provideRetrofit(context: Context): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://www.omdbapi.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(OkHttpClient.Builder().apply {
            addInterceptor {
                Interceptor { chain ->
                    val request: Request = chain.request()
                    val url: HttpUrl =
                        request.url.newBuilder().addQueryParameter("apikey", "b51fb855").build()
                    chain.proceed(request.newBuilder().url(url).build())
                }.intercept(it)
            }
            addInterceptor(ChuckerInterceptor(context))
        }.build())
        .build()

}

fun provideNetworkApi(retrofit: Retrofit): MovieApi =
    retrofit.create(MovieApi::class.java)