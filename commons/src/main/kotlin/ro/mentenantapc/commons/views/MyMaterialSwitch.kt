package ro.mentenantapc.commons.views

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import androidx.appcompat.content.res.AppCompatResources
import com.google.android.material.materialswitch.MaterialSwitch
import ro.mentenantapc.commons.R
import ro.mentenantapc.commons.extensions.adjustAlpha
import ro.mentenantapc.commons.extensions.baseConfig
import ro.mentenantapc.commons.extensions.getContrastColor

class MyMaterialSwitch : MaterialSwitch {
    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle)

    init {
        setShowCheckmark(context.baseConfig.showCheckmarksOnSwitches)
    }

    fun setShowCheckmark(showCheckmark: Boolean) {
        if (showCheckmark) {
            setOnCheckedChangeListener { _, isChecked ->
                setThumbIconDrawable(
                    if (isChecked) {
                        AppCompatResources.getDrawable(context, R.drawable.ic_check_vector)
                    } else {
                        null
                    }
                )
            }
        } else {
            setOnCheckedChangeListener(null)
        }
    }

    fun setColors(textColor: Int, accentColor: Int, backgroundColor: Int) {
        val onPrimary = accentColor.getContrastColor()
        val outlineColor = textColor.adjustAlpha(0.4f)
        val trackColor = textColor.adjustAlpha(0.2f)

        setTextColor(textColor)
        trackTintList = ColorStateList(
            arrayOf(
                intArrayOf(-android.R.attr.state_checked),
                intArrayOf(android.R.attr.state_checked)
            ),
            intArrayOf(trackColor, accentColor)
        )

        thumbTintList = ColorStateList(
            arrayOf(
                intArrayOf(-android.R.attr.state_checked),
                intArrayOf(android.R.attr.state_checked)
            ),
            intArrayOf(outlineColor, onPrimary)
        )

        thumbIconTintList = ColorStateList(
            arrayOf(
                intArrayOf(-android.R.attr.state_checked),
                intArrayOf(android.R.attr.state_checked)
            ),
            intArrayOf(trackColor, accentColor)
        )

        trackDecorationTintList = ColorStateList(
            arrayOf(
                intArrayOf(-android.R.attr.state_checked),
                intArrayOf(android.R.attr.state_checked)
            ),
            intArrayOf(outlineColor, accentColor)
        )
    }
}
