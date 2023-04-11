package com.yasinskyi.android.edu2.di.module.activity

import android.content.Context
import androidx.fragment.app.FragmentActivity
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.yasinskyi.android.edu2.R
import com.yasinskyi.android.edu2.ui.dialog.MessageInterface
import com.yasinskyi.android.edu2.ui.dialog.alert.AlertMessageInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
object MainActivityModule {

    @Provides
    @ActivityScoped
    fun provideNavigator(@ActivityContext context: Context): AppNavigator {
        return AppNavigator(context as FragmentActivity, R.id.fragmentContainerView)
    }

    @Provides
    @ActivityScoped
    fun provideMessageInterface(@ActivityContext context: Context): MessageInterface {
        return AlertMessageInterface(context as FragmentActivity)
    }
}