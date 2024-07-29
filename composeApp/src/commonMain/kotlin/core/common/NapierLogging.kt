package core.common

import com.anime.cmp.AppConfig
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

fun setUpNapierLoggingDebug() {
    if (AppConfig.DEBUG) {
        Napier.base(DebugAntilog())
    }
}