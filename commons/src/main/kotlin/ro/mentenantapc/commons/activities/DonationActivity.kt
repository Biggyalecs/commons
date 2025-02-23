package ro.mentenantapc.commons.activities

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import ro.mentenantapc.commons.R
import ro.mentenantapc.commons.compose.extensions.enableEdgeToEdgeSimple
import ro.mentenantapc.commons.compose.screens.DonationScreen
import ro.mentenantapc.commons.compose.theme.AppThemeSurface
import ro.mentenantapc.commons.extensions.openWebsiteIntent
import ro.mentenantapc.commons.extensions.toast
import ro.mentenantapc.commons.models.Donation

class DonationActivity : BaseComposeActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdgeSimple()
        setContent {
            val clipboardManager = LocalClipboardManager.current
            AppThemeSurface {
                DonationScreen(
                    donationOptions = MentenantaPcDonationPlatforms,
                    cryptoAddresses = MentenantaPcCryptoAddresses,
                    goBack = ::finish,
                    openWebsite = ::openWebsiteIntent,
                    copyToClipboard = {
                        clipboardManager.setText(AnnotatedString(it))
                        toast(R.string.value_copied_to_clipboard)
                    },
                )
            }
        }
    }
}

internal val MentenantaPcDonationPlatforms = listOf(
    Donation.Platform(
        fee = 0,
        link = "https://github.com/sponsors/Biggyalecs",
        nameRes = R.string.github_sponsors,
        iconRes = R.drawable.ic_github_tinted_vector
    ),
    Donation.Platform(
        fee = 0,
        link = "https://liberapay.com/naveensingh",
        nameRes = R.string.liberapay,
        iconRes = R.drawable.ic_liberapay_vector
    ),
    Donation.Platform(
        fee = 10,
        link = "https://www.patreon.com/Biggyalecs",
        nameRes = R.string.patreon,
        iconRes = R.drawable.ic_patreon_vector
    ),
    Donation.Platform(
        fee = 5,
        link = "https://www.paypal.com/donate/?hosted_button_id=JF436PD48C6T2",
        nameRes = R.string.paypal,
        iconRes = R.drawable.ic_paypal_vector
    ),
)

@Suppress("SpellCheckingInspection")
internal val MentenantaPcCryptoAddresses = listOf(
    Donation.Crypto(
        address = "3PrmXk66GCyNp21utmRwSfAnr4BTyWiimw",
        iconRes = R.drawable.ic_bitcoin_vector,
        nameRes = R.string.bitcoin_btc
    ),
    Donation.Crypto(
        address = "0x0ae97452f892a13b727bb1499f87213a99516067",
        iconRes = R.drawable.ic_ethereum_vector,
        nameRes = R.string.ethereum_eth
    ),
    Donation.Crypto(
        address = "Lb61JP7BxwXKUikEbo3CKBEo9tg8bgF2Tr",
        iconRes = R.drawable.ic_litecoin_ltc,
        nameRes = R.string.litecoin_ltc
    ),
    Donation.Crypto(
        address = "TWMNoqCrZDiGQ2miznEHpoBniC7RJpNmec",
        iconRes = R.drawable.ic_tron_vector,
        nameRes = R.string.tron_trx
    )
)
