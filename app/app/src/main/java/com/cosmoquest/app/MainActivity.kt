
package com.cosmoquest.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.cosmoquest.app.ui.navigation.CosmoQuestNavigation
import com.cosmoquest.app.ui.theme.CosmoQuestTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CosmoQuestTheme {
                CosmoQuestNavigation()
            }
        }
    }
}
