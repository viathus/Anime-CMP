package core.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.interop.UIKitView
import androidx.lifecycle.LifecycleOwner
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSURL
import platform.Foundation.NSURLRequest
import platform.WebKit.WKWebView

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun YoutubeVideoPlayer(modifier: Modifier, url: String, lifecycleOwner: LifecycleOwner) {
    UIKitView(
        factory = {
            WKWebView()
        },
        modifier = modifier,
        update = { webView ->
            webView.loadRequest(NSURLRequest(NSURL.URLWithString("https://www.youtube.com/embed/$url")!!))
        }
    )

}
