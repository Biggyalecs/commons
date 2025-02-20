package ro.mentenantapc.commons.helpers

import android.net.Uri

object MyContentProvider {
    private const val AUTHORITY = "ro.mentenantapc.android.provider"
    val MY_CONTENT_URI: Uri = Uri.parse("content://$AUTHORITY/settings")
    const val SHARED_THEME_ACTIVATED = "ro.mentenantapc.commons.SHARED_THEME_ACTIVATED"
    const val SHARED_THEME_UPDATED = "ro.mentenantapc.commons.SHARED_THEME_UPDATED"
    const val ACTION_GLOBAL_CONFIG_UPDATED = "ro.mentenantapc.android.GLOBAL_CONFIG_UPDATED"
    const val PERMISSION_WRITE_GLOBAL_SETTINGS = "ro.mentenantapc.android.permission.WRITE_GLOBAL_SETTINGS"

    const val COL_ID = "_id"    // used in MentenantaPc Thank You
    const val COL_THEME_TYPE = "theme_type"
    const val COL_TEXT_COLOR = "text_color"
    const val COL_BACKGROUND_COLOR = "background_color"
    const val COL_PRIMARY_COLOR = "primary_color"
    const val COL_ACCENT_COLOR = "accent_color"
    const val COL_APP_ICON_COLOR = "app_icon_color"
    const val COL_SHOW_CHECKMARKS_ON_SWITCHES = "show_checkmarks_on_switches"
    const val COL_LAST_UPDATED_TS = "last_updated_ts"

    const val GLOBAL_THEME_DISABLED = 0
    const val GLOBAL_THEME_SYSTEM = 1
    const val GLOBAL_THEME_CUSTOM = 2
}
