package core.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.LifecycleOwner

@Composable
expect fun YoutubeVideoPlayer(modifier: Modifier, url: String, lifecycleOwner: LifecycleOwner)

const val baseYoutubeUrl = "https://www.youtube.com/watch?v="
