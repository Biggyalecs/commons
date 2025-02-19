package ro.mentenantapc.commons.extensions

import android.content.Context
import ro.mentenantapc.commons.models.FileDirItem

fun FileDirItem.isRecycleBinPath(context: Context): Boolean {
    return path.startsWith(context.recycleBinPath)
}
