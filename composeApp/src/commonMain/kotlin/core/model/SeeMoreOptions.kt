package core.model

import androidx.core.bundle.Bundle
import androidx.navigation.NavType
import kotlinx.serialization.Serializable

@Serializable
enum class SeeMoreOptions {
    TRENDING,
    UPCOMING,
    MOST_POPULAR
}


class SeeMoreOptionsNavType : NavType<SeeMoreOptions>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): SeeMoreOptions? {
        return SeeMoreOptions.entries.firstOrNull { it.name == bundle.getString(key) }
    }

    override fun parseValue(value: String): SeeMoreOptions {
        return SeeMoreOptions.entries.first { it.name == value }
    }

    override fun put(bundle: Bundle, key: String, value: SeeMoreOptions) {
        bundle.putString(key, value.name)
    }
}