package com.saraiva.bipa

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class HiltApplication @Inject constructor(): Application() {
}