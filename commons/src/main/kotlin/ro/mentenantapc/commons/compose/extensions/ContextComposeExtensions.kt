package ro.mentenantapc.commons.compose.extensions

import android.app.Activity
import android.content.Context
import ro.mentenantapc.commons.R
import ro.mentenantapc.commons.extensions.baseConfig
import ro.mentenantapc.commons.extensions.redirectToRateUs
import ro.mentenantapc.commons.extensions.toast
import ro.mentenantapc.commons.helpers.BaseConfig

val Context.config: BaseConfig get() = BaseConfig.newInstance(applicationContext)

fun Activity.rateStarsRedirectAndThankYou(stars: Int) {
    if (stars == 5) {
        redirectToRateUs()
    }
    toast(R.string.thank_you)
    baseConfig.wasAppRated = true
}
