package com.sohan.candyblast

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.Toast

class MainActivity : Activity() {

    private lateinit var webView: WebView
    private lateinit var rootLayout: FrameLayout

    private val gameUrl =
        "https://sbavne987-dev.github.io/Candy-blast-game/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        rootLayout = FrameLayout(this)
        setContentView(rootLayout)

        // Splash Screen Image
        val splashImage = ImageView(this)
        splashImage.setImageResource(R.drawable.candy_splash)
        splashImage.scaleType = ImageView.ScaleType.CENTER_CROP

        rootLayout.addView(
            splashImage,
            FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
            )
        )

        // WebView setup
        webView = WebView(this)

        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true
        webView.settings.mediaPlaybackRequiresUserGesture = false
        webView.settings.allowFileAccess = false

        webView.webChromeClient = WebChromeClient()
        webView.webViewClient = WebViewClient()

        webView.setBackgroundColor(android.graphics.Color.BLACK)

        // Show game after splash screen
        Handler(Looper.getMainLooper()).postDelayed({

            if (isFinishing || isDestroyed) return@postDelayed

            rootLayout.removeView(splashImage)

            rootLayout.addView(
                webView,
                FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.MATCH_PARENT
                )
            )

            if (gameUrl.startsWith("https://")) {
                webView.loadUrl(gameUrl)
            } else {
                Toast.makeText(
                    this,
                    "Invalid game website URL",
                    Toast.LENGTH_LONG
                ).show()
            }

        }, 2000)
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (::webView.isInitialized && webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }

    override fun onDestroy() {
        if (::webView.isInitialized) {
            webView.destroy()
        }
        super.onDestroy()
    }
}
