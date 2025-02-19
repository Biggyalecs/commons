package ro.mentenantapc.commons

import android.app.Application
import androidx.lifecycle.ProcessLifecycleOwner
import ro.mentenantapc.commons.extensions.appLockManager
import ro.mentenantapc.commons.extensions.checkUseEnglish

open class MentenantaPcApp : Application() {

    open val isAppLockFeatureAvailable = false

    override fun onCreate() {
        super.onCreate()
        checkUseEnglish()
        setupAppLockManager()
    }

    private fun setupAppLockManager() {
        if (isAppLockFeatureAvailable) {
            ProcessLifecycleOwner.get().lifecycle.addObserver(appLockManager)
        }
    }
}
