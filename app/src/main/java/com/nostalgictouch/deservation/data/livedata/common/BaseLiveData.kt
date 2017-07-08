package com.nostalgictouch.deservation.data.livedata.common

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.nostalgictouch.whatshot.viewmodel.trends.Status

open class BaseLiveData<T> : MutableLiveData<T>() {

    private var mLoadingStatus = MutableLiveData<Status>()

    init {
        mLoadingStatus.value = Status.NONE
    }

    val loadingStatus: LiveData<Status>
        get() = mLoadingStatus
}