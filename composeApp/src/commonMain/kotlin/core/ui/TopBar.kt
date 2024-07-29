package core.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import animecmp.composeapp.generated.resources.Res
import animecmp.composeapp.generated.resources.in_app_logo
import core.designsystem.theme.LocalColors
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(showIcon: Boolean = true, onBackClick: (() -> Unit)? = null) {
    CenterAlignedTopAppBar(
        title = {
            if (showIcon) {
                Image(
                    modifier = Modifier.size(48.dp),
                    painter = painterResource(Res.drawable.in_app_logo),
                    contentDescription = "Logo",
                )
            }
        },
        navigationIcon = {
            onBackClick?.let {
                IconButton(onClick = it) {
                    Icon(
                        modifier = Modifier.size(32.dp),
                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = "Go Back",
                        tint = LocalColors.current.onPrimary,
                    )
                }
            }
        },

        colors = TopAppBarDefaults.topAppBarColors().copy(
            containerColor = Color.Transparent,
        ),
    )
}