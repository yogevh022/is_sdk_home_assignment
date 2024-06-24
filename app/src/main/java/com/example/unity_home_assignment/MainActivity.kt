package com.example.unity_home_assignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unity_home_assignment.ui.theme.Unity_Home_AssignmentTheme
import com.ironsource.mediationsdk.IronSource
import com.ironsource.mediationsdk.adunit.adapter.utility.AdInfo
import com.ironsource.mediationsdk.integration.IntegrationHelper
import com.ironsource.mediationsdk.logger.IronSourceError
import com.ironsource.mediationsdk.sdk.LevelPlayInterstitialListener

class ImpLevelPlayInterstitialListener : LevelPlayInterstitialListener {
    // quick implementation of the LevelPlayInterstitialListener interface
    // only implementing what is needed for this demo

    override fun onAdReady(p0: AdInfo?) {
        // Display the ad immediately when its done loading
        IronSource.showInterstitial()
    }

    override fun onAdClosed(p0: AdInfo?) {
    }

    override fun onAdOpened(p0: AdInfo?) {
    }

    override fun onAdClicked(p0: AdInfo?) {
    }

    override fun onAdLoadFailed(p0: IronSourceError?) {
    }

    override fun onAdShowSucceeded(p0: AdInfo?) {
    }

    override fun onAdShowFailed(p0: IronSourceError?, p1: AdInfo?) {
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setupLevelPlay()

        enableEdgeToEdge()
        setContent {
            Unity_Home_AssignmentTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    innerPadding ->
                    Column(modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        ShowAdButton(
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        IronSource.onResume(this)
    }

    override fun onPause() {
        super.onPause()
        IronSource.onPause(this)
    }

    private fun setupLevelPlay() {
        IronSource.init(this, "85460dcd") {
            // LevelPlay initialized

            // setup my implementation of the LevelPlayInterstitialListener
            val oLevelPlayInterstitialListener = ImpLevelPlayInterstitialListener()
            IronSource.setLevelPlayInterstitialListener(oLevelPlayInterstitialListener)
            // vv not needed anymore after successful integration validation
            // IntegrationHelper.validateIntegration(this)
        }
    }
}

@Composable
fun ShowAdButton(modifier: Modifier = Modifier) {
    Button(
        onClick = {
            // Load interstitial ad on click, the ad will run immediately after loading.
            // interstitial is not loaded in advance because it runs on click for demo purposes
            IronSource.loadInterstitial()
        },
        modifier = modifier.padding(24.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.hsv(236f,0.72f,0.83f)
        )
    ) {
        Text(
            text = "Click to Show Interstitial Ad!",
            modifier = modifier.padding(24.dp),
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        )
    }
}
