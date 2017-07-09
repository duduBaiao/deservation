package com.nostalgictouch.deservation.di

import com.nostalgictouch.deservation.data.livedata.CustomersLiveData
import com.nostalgictouch.deservation.data.livedata.TablesLiveData
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    fun inject(customersLiveData: CustomersLiveData)

    fun inject(tablesLiveData: TablesLiveData)
}
