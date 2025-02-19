package ro.mentenantapc.commons.compose.screens

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ContentCopy
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ro.mentenantapc.commons.R
import ro.mentenantapc.commons.activities.FossifyCryptoAddresses
import ro.mentenantapc.commons.activities.FossifyDonationPlatforms
import ro.mentenantapc.commons.compose.lists.SimpleColumnScaffold
import ro.mentenantapc.commons.compose.settings.SettingsGroup
import ro.mentenantapc.commons.compose.settings.SettingsHorizontalDivider
import ro.mentenantapc.commons.compose.settings.SettingsTitleTextComponent
import ro.mentenantapc.commons.compose.theme.Shapes
import ro.mentenantapc.commons.compose.theme.SimpleTheme
import ro.mentenantapc.commons.compose.theme.textSubTitleColor
import ro.mentenantapc.commons.models.Donation

private val titleStartPadding = Modifier.padding(start = 48.dp)

@Composable
fun DonationScreen(
    donationOptions: List<Donation.Platform>,
    cryptoAddresses: List<Donation.Crypto>,
    goBack: () -> Unit,
    openWebsite: (String) -> Unit,
    copyToClipboard: (String) -> Unit,
) {
    SimpleColumnScaffold(title = stringResource(id = R.string.donate_to_fossify), goBack = goBack) {
        DonationPlatforms(
            options = donationOptions,
            copyToClipboard = copyToClipboard,
            openWebsite = openWebsite
        )

        SettingsHorizontalDivider()

        DonationCryptos(
            options = cryptoAddresses,
            copyToClipboard = copyToClipboard,
        )
    }
}

@Composable
fun DonationPlatforms(
    options: List<Donation.Platform>,
    openWebsite: (String) -> Unit,
    copyToClipboard: (String) -> Unit,
) {
    SettingsGroup(title = {
        SettingsTitleTextComponent(
            text = stringResource(id = R.string.platforms),
            modifier = titleStartPadding
        )
    }) {
        options.forEach {
            DonationListItem(
                name = stringResource(it.nameRes),
                description = if (it.fee == 0) {
                    stringResource(id = R.string.little_to_no_fee)
                } else {
                    stringResource(R.string.fee_up_to_pct, it.fee)
                },
                icon = it.iconRes,
                onClick = { openWebsite(it.link) },
                onCopyClick = { copyToClipboard(it.link) },
            )
        }
    }
}

@Composable
fun DonationCryptos(
    options: List<Donation.Crypto>,
    copyToClipboard: (String) -> Unit,
) {
    SettingsGroup(title = {
        SettingsTitleTextComponent(
            text = stringResource(id = R.string.cryptocurrency),
            modifier = titleStartPadding
        )
    }) {
        options.forEach {
            DonationListItem(
                name = stringResource(it.nameRes),
                description = it.address,
                icon = it.iconRes,
                onClick = { copyToClipboard(it.address) },
                onCopyClick = { copyToClipboard(it.address) },
            )
        }
    }
}

@Composable
fun DonationListItem(
    name: String,
    description: String,
    @DrawableRes icon: Int,
    onClick: () -> Unit,
    onCopyClick: () -> Unit,
) {
    ListItem(
        modifier = Modifier
            .clickable(onClick = onClick),
        headlineContent = {
            Text(
                text = name,
                modifier = Modifier
                    .fillMaxWidth(),
            )
        },
        leadingContent = {
            Image(
                modifier = Modifier
                    .size(SimpleTheme.dimens.icon.small),
                painter = painterResource(id = icon),
                contentDescription = name,
            )
        },
        supportingContent = {
            Text(
                text = description,
                style = SimpleTheme.typography.bodyMedium.copy(color = textSubTitleColor)
            )
        },
        trailingContent = {
            Icon(
                imageVector = Icons.Rounded.ContentCopy,
                contentDescription = stringResource(id = R.string.copy_to_clipboard),
                tint = SimpleTheme.colorScheme.primary,
                modifier = Modifier
                    .clip(Shapes.extraLarge)
                    .clickable(onClick = onCopyClick)
                    .padding(12.dp),
            )
        }
    )
}

@Composable
@Preview
fun PreviewDonationScreen() {
    DonationScreen(
        donationOptions = FossifyDonationPlatforms,
        cryptoAddresses = FossifyCryptoAddresses,
        goBack = {},
        openWebsite = {},
        copyToClipboard = {},
    )
}
