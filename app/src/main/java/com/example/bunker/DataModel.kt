package com.example.bunker

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class DataModel: ViewModel() {
    val message: MutableLiveData<Fragment> by lazy {
        MutableLiveData<Fragment>()
    }
}
open class MusicDataModel: ViewModel() {
    val message: MutableLiveData<Void> by lazy {
        MutableLiveData<Void>()
    }
}