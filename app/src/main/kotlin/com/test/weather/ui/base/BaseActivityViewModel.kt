package com.test.weather.ui.base

import android.os.Bundle
import android.view.View
import androidx.lifecycle.MutableLiveData
import kotlin.reflect.KClass

abstract class BaseActivityViewModel : BaseViewModel() {

    lateinit var errorClickListener : View.OnClickListener

    val activityToStart = MutableLiveData<Pair<KClass<*>, Bundle?>>()

    val errorMessage: MutableLiveData<Any> = MutableLiveData()

}