package com.test.weather.injection

import android.content.Context
import com.test.weather.injection.component.ActivityComponent
import com.test.weather.ui.App
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ComponentHandler @Inject constructor(val context: Context,
                                           val activityComponentBuilder: ActivityComponent.Builder) {

    private val app: App = context.getApplicationContext() as App
    private lateinit var activityComponent: ActivityComponent

    fun buildRequiredComponent() {
        activityComponent = activityComponentBuilder
            .build()

        activityComponent.inject(app)
    }
}