package ro.mentenantapc.commons.compose.settings

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import ro.mentenantapc.commons.compose.extensions.BooleanPreviewParameterProvider
import ro.mentenantapc.commons.compose.extensions.MyDevices
import ro.mentenantapc.commons.compose.extensions.rememberMutableInteractionSource
import ro.mentenantapc.commons.compose.theme.AppThemeSurface
import ro.mentenantapc.commons.compose.theme.SimpleTheme
import ro.mentenantapc.commons.compose.theme.preferenceLabelColor
import ro.mentenantapc.commons.compose.theme.preferenceValueColor

@Composable
fun SettingsSwitchComponent(
    modifier: Modifier = Modifier,
    label: String,
    value: String? = null,
    initialValue: Boolean = false,
    isPreferenceEnabled: Boolean = true,
    showCheckmark: Boolean,
    onChange: ((Boolean) -> Unit)? = null,
    switchColors: SwitchColors = SwitchDefaults.colors()
) {
    val interactionSource = rememberMutableInteractionSource()
    val indication = LocalIndication.current

    ListItem(
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                enabled = isPreferenceEnabled,
                onClick = { onChange?.invoke(!initialValue) },
                interactionSource = interactionSource,
                indication = indication
            ),
        colors = ListItemDefaults.colors(
            headlineColor = preferenceLabelColor(isEnabled = isPreferenceEnabled),
            supportingColor = preferenceValueColor(isEnabled = isPreferenceEnabled)
        ),
        headlineContent = {
            Text(text = label)
        },
        supportingContent = {
            AnimatedVisibility(visible = !value.isNullOrBlank()) {
                Text(text = value.toString())
            }
        },
        trailingContent = {
            CompositionLocalProvider(LocalRippleConfiguration provides null) {
                Switch(
                    checked = initialValue,
                    onCheckedChange = { onChange?.invoke(it) },
                    thumbContent = if (showCheckmark && initialValue) {
                        {
                            Icon(
                                modifier = Modifier.size(SwitchDefaults.IconSize),
                                imageVector = Icons.Default.Check,
                                contentDescription = null,
                                tint = SimpleTheme.colorScheme.primary
                            )
                        }
                    } else null,
                    enabled = isPreferenceEnabled,
                    colors = switchColors,
                    interactionSource = interactionSource,
                )
            }
        }
    )
}

@MyDevices
@Composable
private fun SettingsSwitchComponentPreview(@PreviewParameter(BooleanPreviewParameterProvider::class) isChecked: Boolean) {
    var checked by remember { mutableStateOf(isChecked) }
    AppThemeSurface {
        SettingsSwitchComponent(
            label = "Some label",
            value = "Some value",
            initialValue = checked,
            showCheckmark = false,
            onChange = {
                checked = it
            }
        )
    }
}
