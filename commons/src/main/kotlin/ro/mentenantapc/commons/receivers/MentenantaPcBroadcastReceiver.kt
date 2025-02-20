package ro.mentenantapc.commons.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import ro.mentenantapc.commons.extensions.syncGlobalConfig
import ro.mentenantapc.commons.helpers.MyContentProvider

class MentenantaPcBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == MyContentProvider.ACTION_GLOBAL_CONFIG_UPDATED) {
            context?.syncGlobalConfig()
        }
    }
}
