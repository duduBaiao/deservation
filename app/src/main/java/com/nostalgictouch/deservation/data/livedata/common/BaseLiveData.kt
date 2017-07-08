package com.nostalgictouch.deservation.data.livedata.common

import android.arch.lifecycle.MutableLiveData

open class BaseLiveData<T> : MutableLiveData<T>() {

    private var mLoadingStatus = MutableLiveData<Status>()

    init {
        mLoadingStatus.value = Status.NONE
    }

    val loadingStatus: MutableLiveData<Status>
        get() = mLoadingStatus
}