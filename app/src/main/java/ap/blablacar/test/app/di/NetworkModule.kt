package ap.blablacar.test.app.di

import ap.blablacar.test.BuildConfig
import ap.blablacar.test.data.oauth.OAuthApi
import ap.blablacar.test.data.oauth.OAuthInterceptor
import ap.blablacar.test.data.trip.TripApi
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


fun createoAuthOkHttpClient(oauthInterceptor: OAuthInterceptor): OkHttpClient {
    val logInterceptor =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
    return OkHttpClient.Builder()
        .connectTimeout(30L, TimeUnit.SECONDS)
        .readTimeout(30L, TimeUnit.SECONDS)
        .addInterceptor(logInterceptor)
        .addInterceptor(oauthInterceptor)
        .build()
}

fun createOkHttpClient(): OkHttpClient {
    val logInterceptor =
        HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
    return OkHttpClient.Builder()
        .connectTimeout(30L, TimeUnit.SECONDS)
        .readTimeout(30L, TimeUnit.SECONDS)
        .addInterceptor(logInterceptor)
        .build()
}

fun createRetrofit(okHttp: OkHttpClient): Retrofit = Retrofit.Builder()
    .baseUrl(BuildConfig.API_URL)
    .client(okHttp)
    .addConverterFactory(
        GsonConverterFactory.create(GsonBuilder().create())
    )
    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
    .build()

val networkModule = module {

    single { OAuthInterceptor(get()) }
    single { createRetrofit(okHttp = createOkHttpClient()).create(OAuthApi::class.java) }
    single { createRetrofit(okHttp = createoAuthOkHttpClient(get())).create(TripApi::class.java) }


}


