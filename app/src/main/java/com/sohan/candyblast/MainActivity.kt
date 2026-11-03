package com.sohan.candyblast

import android.app.Activity
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.view.View
import android.view.WindowManager
import android.widget.Toast

class MainActivity : Activity() {

    private lateinit var webView: WebView

    // अपनी GitHub Pages वेबसाइट का URL यहाँ डालना
    private val gameUrl = "https://sbavne987-dev.github.io/Candy-blast-game/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        webView = WebView(this)

        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true
        webView.settings.mediaPlaybackRequiresUserGesture = false
        webView.settings.allowFileAccess = false

        webView.webChromeClient = WebChromeClient()
        webView.webViewClient = WebViewClient()

        webView.setBackgroundColor(android.graphics.Color.BLACK)

        setContentView(webView)

        if (gameUrl.startsWith("https://")) {
            webView.loadUrl(gameUrl)
        } else {
            Toast.makeText(
                this,
                "Please add your game website URL",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }

    override fun onDestroy() {
        webView.destroy()
        super.onDestroy()
    }
}
