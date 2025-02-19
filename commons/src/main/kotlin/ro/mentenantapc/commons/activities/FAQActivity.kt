package ro.mentenantapc.commons.activities

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.runtime.remember
import kotlinx.collections.immutable.toImmutableList
import ro.mentenantapc.commons.compose.extensions.enableEdgeToEdgeSimple
import ro.mentenantapc.commons.compose.screens.FAQScreen
import ro.mentenantapc.commons.compose.theme.AppThemeSurface
import ro.mentenantapc.commons.helpers.APP_FAQ
import ro.mentenantapc.commons.models.FAQItem

class FAQActivity : BaseComposeActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdgeSimple()
        setContent {
            AppThemeSurface {
                val faqItems = remember { intent.getSerializableExtra(APP_FAQ) as ArrayList<FAQItem> }
                FAQScreen(
                    goBack = ::finish,
                    faqItems = faqItems.toImmutableList()
                )
            }
        }
    }
}
