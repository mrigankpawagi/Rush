package com.mrigankpawagi.rush.app

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil3.ImageLoader
import com.mrigankpawagi.rush.billing.PaywallPage
import com.mrigankpawagi.rush.core.data.listener.NotificationListener
import com.mrigankpawagi.rush.core.presentation.RushTheme
import com.mrigankpawagi.rush.lyrics.LyricsGraph
import com.mrigankpawagi.rush.onboarding.Onboarding
import com.mrigankpawagi.rush.saved.SavedPage
import com.mrigankpawagi.rush.search_sheet.SearchSheet
import com.mrigankpawagi.rush.setting.SettingsGraph
import com.mrigankpawagi.rush.setting.SettingsPageAction
import com.mrigankpawagi.rush.share.SharePage
import com.mrigankpawagi.rush.viewmodels.LyricsVM
import com.mrigankpawagi.rush.viewmodels.SavedVM
import com.mrigankpawagi.rush.viewmodels.SearchSheetVM
import com.mrigankpawagi.rush.viewmodels.SettingsVM
import com.mrigankpawagi.rush.viewmodels.ShareVM
import com.skydoves.landscapist.coil3.LocalCoilImageLoader
import kotlinx.serialization.Serializable
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel

@Serializable
sealed interface Route {
    @Serializable
    data object SavedPage : Route

    @Serializable
    data object LyricsGraph : Route

    @Serializable
    data object SettingsGraph : Route

    @Serializable
    data object SharePage : Route

    @Serializable
    data object OnboardingPage : Route
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RushApp() {
    val settingsVM: SettingsVM = koinViewModel()
    val searchSheetVM: SearchSheetVM = koinViewModel()
    val lyricsVM: LyricsVM = koinViewModel()

    val lyricsState by lyricsVM.state.collectAsStateWithLifecycle()
    val settingsState by settingsVM.state.collectAsStateWithLifecycle()
    val searchState by searchSheetVM.state.collectAsStateWithLifecycle()

    val imageLoader: ImageLoader = koinInject()

    val navController = rememberNavController()
    val context = LocalContext.current

    LaunchedEffect(settingsState.onBoardingDone) {
        if (!settingsState.onBoardingDone) {
            navController.navigate(Route.OnboardingPage)
        }
    }

    CompositionLocalProvider(
        LocalCoilImageLoader provides imageLoader
    ) {
        RushTheme(
            theme = settingsState.theme
        ) {
            AnimatedContent(
                targetState = settingsState.showPaywall,
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
            ) {
                if (!it) {
                    NavHost(
                        enterTransition = { fadeIn() },
                        exitTransition = { fadeOut() },
                        popEnterTransition = { fadeIn() },
                        popExitTransition = { fadeOut() },
                        navController = navController,
                        startDestination = Route.SavedPage,
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.background)
                            .fillMaxSize()
                    ) {
                        composable<Route.SavedPage> {
                            val savedVM: SavedVM = koinViewModel()
                            val savedState by savedVM.state.collectAsStateWithLifecycle()

                            SavedPage(
                                state = savedState,
                                notificationAccess = NotificationListener.canAccessNotifications(context),
                                onAction = savedVM::onAction,
                                onNavigateToLyrics = { navController.navigate(Route.LyricsGraph) },
                                onNavigateToSettings = { navController.navigate(Route.SettingsGraph) },
                                modifier = Modifier.widthIn(max = 700.dp)
                            )
                        }

                        composable<Route.LyricsGraph> {
                            LyricsGraph(
                                notificationAccess = NotificationListener.canAccessNotifications(context),
                                lyricsState = lyricsState,
                                lyricsAction = lyricsVM::onAction,
                                onDismiss = { navController.navigateUp() },
                                onShare = {
                                    navController.navigate(Route.SharePage) {
                                        launchSingleTop = true
                                    }
                                }
                            )
                        }

                        composable<Route.SharePage> {
                            val shareVM: ShareVM = koinViewModel()
                            val shareState by shareVM.state.collectAsStateWithLifecycle()

                            SharePage(
                                onDismiss = { navController.navigateUp() },
                                state = shareState,
                                onAction = shareVM::onAction
                            )
                        }

                        composable<Route.SettingsGraph> {
                            SettingsGraph(
                                notificationAccess = NotificationListener.canAccessNotifications(context),
                                state = settingsState,
                                action = settingsVM::onAction,
                                onNavigateBack = { navController.navigateUp() }
                            )
                        }

                        composable<Route.OnboardingPage> {
                            Onboarding(
                                onDone = {
                                    settingsVM.onAction(
                                        SettingsPageAction.OnUpdateOnboardingDone(true)
                                    )
                                    navController.navigateUp()
                                }
                            )
                        }
                    }

                    SearchSheet(
                        state = searchState,
                        onAction = searchSheetVM::onAction,
                        onNavigateToLyrics = {
                            navController.navigate(Route.LyricsGraph) {
                                launchSingleTop = true
                            }
                        },
                        sheetState = rememberModalBottomSheetState()
                    )
                } else {
                    PaywallPage(
                        isProUser = settingsState.isProUser,
                        onDismissRequest = { settingsVM.onAction(SettingsPageAction.OnDismissPaywall) }
                    )
                }
            }
        }
    }
}