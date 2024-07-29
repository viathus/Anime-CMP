package core.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.LifecycleOwner
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@Composable
actual fun YoutubeVideoPlayer(modifier: Modifier, url: String, lifecycleOwner: LifecycleOwner) {
    val frameBuilder = IFramePlayerOptions.Builder().apply {
        fullscreen(0)
        controls(1)
        ccLoadPolicy(1)
    }.build()

    val playerListener = object : AbstractYouTubePlayerListener() {
        override fun onReady(youTubePlayer: YouTubePlayer) {
            youTubePlayer.cueVideo(url, 0f)
        }
    }
    AndroidView(modifier = Modifier, factory = { context ->
        YouTubePlayerView(context = context).apply {
            lifecycleOwner.lifecycle.addObserver(this)
            enableAutomaticInitialization = false
            initialize(playerListener, frameBuilder)
        }
    }, update = {

    }
    )
}