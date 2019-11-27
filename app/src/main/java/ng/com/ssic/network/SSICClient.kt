package ng.com.ssic.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * This creates a Retrofit instance
 */
object SSICClient {

    private const val BASE_URL = "http://41.78.159.43:9909/api/v1/"

    /**
     * Creates [HttpLoggingInterceptor] object for logging purposes
     */
    private fun getLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    /**
     * Creates [OkHttpClient] object for sending and receiving requests
     */
    private fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(getLoggingInterceptor())
            .addInterceptor {
                val request = it.request().newBuilder().addHeader("appkey", "olukRL0vvSeR9Mu4rin7WqSWwOVBmWnROq").build()
                it.proceed(request)
            }
            .connectTimeout(2, TimeUnit.MINUTES)
            .readTimeout(2, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .build()
    }

    /**
     * Generates an implementation of the [SSICService] interface
     */
    fun getService(): SSICService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(getOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SSICService::class.java)
    }
}