package ro.mentenantapc.commons.samples

import com.github.ajalt.reprint.core.Reprint
import ro.mentenantapc.commons.MentenantaPcApp

class App : MentenantaPcApp() {
    override fun onCreate() {
        super.onCreate()
        Reprint.initialize(this)
    }
}
