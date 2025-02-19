package ro.mentenantapc.commons.samples.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import ro.mentenantapc.commons.activities.BaseSimpleActivity
import ro.mentenantapc.commons.activities.ManageBlockedNumbersActivity
import ro.mentenantapc.commons.compose.alert_dialog.AlertDialogState
import ro.mentenantapc.commons.compose.alert_dialog.rememberAlertDialogState
import ro.mentenantapc.commons.compose.extensions.*
import ro.mentenantapc.commons.compose.theme.AppThemeSurface
import ro.mentenantapc.commons.dialogs.ConfirmationDialog
import ro.mentenantapc.commons.dialogs.DonateAlertDialog
import ro.mentenantapc.commons.dialogs.RateStarsAlertDialog
import ro.mentenantapc.commons.extensions.appLaunched
import ro.mentenantapc.commons.extensions.launchMoreAppsFromUsIntent
import ro.mentenantapc.commons.extensions.launchViewIntent
import ro.mentenantapc.commons.helpers.LICENSE_AUTOFITTEXTVIEW
import ro.mentenantapc.commons.models.FAQItem
import ro.mentenantapc.commons.samples.BuildConfig
import ro.mentenantapc.commons.samples.R
import ro.mentenantapc.commons.samples.screens.MainScreen

class MainActivity : BaseSimpleActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appLaunched(BuildConfig.APPLICATION_ID)
        enableEdgeToEdgeSimple()
        setContent {
            AppThemeSurface {
                val showMoreApps = onEventValue { !resources.getBoolean(ro.mentenantapc.commons.R.bool.hide_google_relations) }

                MainScreen(
                    openColorCustomization = ::startCustomizationActivity,
                    manageBlockedNumbers = {
                        startActivity(Intent(this@MainActivity, ManageBlockedNumbersActivity::class.java))
                    },
                    showComposeDialogs = {
                        startActivity(Intent(this@MainActivity, TestDialogActivity::class.java))
                    },
                    openTestButton = {
                        ConfirmationDialog(
                            this@MainActivity,
                            FAKE_VERSION_APP_LABEL,
                            positive = ro.mentenantapc.commons.R.string.ok,
                            negative = 0
                        ) {
                            launchViewIntent(DEVELOPER_PLAY_STORE_URL)
                        }
                    },
                    showMoreApps = showMoreApps,
                    openAbout = ::launchAbout,
                    moreAppsFromUs = ::launchMoreAppsFromUsIntent
                )
                AppLaunched()
            }
        }
    }

    @Composable
    private fun AppLaunched(
        donateAlertDialogState: AlertDialogState = getDonateAlertDialogState(),
        rateStarsAlertDialogState: AlertDialogState = getRateStarsAlertDialogState(),
    ) {
        LaunchedEffect(Unit) {
            appLaunchedCompose(
                appId = BuildConfig.APPLICATION_ID,
                showDonateDialog = donateAlertDialogState::show,
                showRateUsDialog = rateStarsAlertDialogState::show,
                showUpgradeDialog = {}
            )
        }
    }

    @Composable
    private fun getDonateAlertDialogState() =
        rememberAlertDialogState().apply {
            DialogMember {
                DonateAlertDialog(alertDialogState = this)
            }
        }

    @Composable
    private fun getRateStarsAlertDialogState() = rememberAlertDialogState().apply {
        DialogMember {
            RateStarsAlertDialog(alertDialogState = this, onRating = ::rateStarsRedirectAndThankYou)
        }
    }

    private fun launchAbout() {
        val licenses = LICENSE_AUTOFITTEXTVIEW

        val faqItems = arrayListOf(
            FAQItem(ro.mentenantapc.commons.R.string.faq_1_title_commons, ro.mentenantapc.commons.R.string.faq_1_text_commons),
            FAQItem(ro.mentenantapc.commons.R.string.faq_1_title_commons, ro.mentenantapc.commons.R.string.faq_1_text_commons),
            FAQItem(ro.mentenantapc.commons.R.string.faq_4_title_commons, ro.mentenantapc.commons.R.string.faq_4_text_commons)
        )

        if (!resources.getBoolean(ro.mentenantapc.commons.R.bool.hide_google_relations)) {
            faqItems.add(FAQItem(ro.mentenantapc.commons.R.string.faq_2_title_commons,
                ro.mentenantapc.commons.R.string.faq_2_text_commons))
            faqItems.add(FAQItem(ro.mentenantapc.commons.R.string.faq_6_title_commons,
                ro.mentenantapc.commons.R.string.faq_6_text_commons))
        }

        startAboutActivity(
            appNameId = R.string.commons_app_name,
            licenseMask = licenses,
            versionName = BuildConfig.VERSION_NAME,
            faqItems = faqItems,
            showFAQBeforeMail = true,
        )
    }

    override fun getAppLauncherName() = getString(R.string.commons_app_name)

    override fun getAppIconIDs() = arrayListOf(
        R.mipmap.ic_launcher_red,
        R.mipmap.ic_launcher_pink,
        R.mipmap.ic_launcher_purple,
        R.mipmap.ic_launcher_deep_purple,
        R.mipmap.ic_launcher_indigo,
        R.mipmap.ic_launcher_blue,
        R.mipmap.ic_launcher_light_blue,
        R.mipmap.ic_launcher_cyan,
        R.mipmap.ic_launcher_teal,
        R.mipmap.ic_launcher,
        R.mipmap.ic_launcher_light_green,
        R.mipmap.ic_launcher_lime,
        R.mipmap.ic_launcher_yellow,
        R.mipmap.ic_launcher_amber,
        R.mipmap.ic_launcher_orange,
        R.mipmap.ic_launcher_deep_orange,
        R.mipmap.ic_launcher_brown,
        R.mipmap.ic_launcher_blue_grey,
        R.mipmap.ic_launcher_grey_black
    )

    override fun getRepositoryName() = "General-Discussion"
}
