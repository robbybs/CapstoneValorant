package com.rbs.capstonevalorant.di

import androidx.room.Room
import com.rbs.core.data.AgentsRepository
import com.rbs.core.data.local.LocalDataSource
import com.rbs.core.data.local.room.AgentsDatabase
import com.rbs.core.data.remote.RemoteDataSource
import com.rbs.core.data.remote.network.ApiService
import com.rbs.core.domain.repository.IAgentsRepository
import com.rbs.core.utils.AppExecutors
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<AgentsDatabase>().agentsDao() }
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("rbs".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            AgentsDatabase::class.java, "Agents.db"
        ).fallbackToDestructiveMigration().openHelperFactory(factory).build()
    }
}

val networkModule = module {
    single {
        val hostname = "valorant-api.com"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/R4oHF9eGOA8qKZz5ncBndEU80F4w3zZg95/Lh3Y4UlM=")
            .build()
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://valorant-api.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IAgentsRepository> { AgentsRepository(get(), get(), get()) }
}