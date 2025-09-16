package com.mrigankpawagi.rush.di

import com.mrigankpawagi.rush.billing.BillingHandler
import com.mrigankpawagi.rush.billing.BillingHandlerImpl
import com.mrigankpawagi.rush.core.data.DatastoreFactory
import com.mrigankpawagi.rush.core.data.HttpClientFactory
import com.mrigankpawagi.rush.core.data.LyricsPagePreferencesImpl
import com.mrigankpawagi.rush.core.data.OtherPreferencesImpl
import com.mrigankpawagi.rush.core.data.PaletteGenerator
import com.mrigankpawagi.rush.core.data.SharePagePreferencesImpl
import com.mrigankpawagi.rush.core.data.backup.ExportImpl
import com.mrigankpawagi.rush.core.data.backup.RestoreImpl
import com.mrigankpawagi.rush.core.data.database.DatabaseFactory
import com.mrigankpawagi.rush.core.data.database.SongDatabase
import com.mrigankpawagi.rush.core.data.network.GeniusApi
import com.mrigankpawagi.rush.core.data.network.GeniusScraper
import com.mrigankpawagi.rush.core.data.network.LrcLibApi
import com.mrigankpawagi.rush.core.data.repository.RushRepository
import com.mrigankpawagi.rush.core.domain.LyricsPagePreferences
import com.mrigankpawagi.rush.core.domain.OtherPreferences
import com.mrigankpawagi.rush.core.domain.SharePagePreferences
import com.mrigankpawagi.rush.core.domain.SongRepo
import com.mrigankpawagi.rush.core.domain.backup.ExportRepo
import com.mrigankpawagi.rush.core.domain.backup.RestoreRepo
import com.mrigankpawagi.rush.viewmodels.LyricsVM
import com.mrigankpawagi.rush.viewmodels.SavedVM
import com.mrigankpawagi.rush.viewmodels.SearchSheetVM
import com.mrigankpawagi.rush.viewmodels.SettingsVM
import com.mrigankpawagi.rush.viewmodels.ShareVM
import com.mrigankpawagi.rush.viewmodels.StateLayer
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

val rushModules = module {
    // billing
    singleOf(::BillingHandlerImpl).bind<BillingHandler>()

    // factories, listeners, generators and backup stuff
    singleOf(::DatabaseFactory)
    singleOf(::DatastoreFactory)
    single { get<DatabaseFactory>().create().build() }
    singleOf(::ExportImpl).bind<ExportRepo>()
    singleOf(::RestoreImpl).bind<RestoreRepo>()
    singleOf(::PaletteGenerator)

    // android specific imageloader with cache
    singleOf(::provideImageLoader)

    // Database
    single { get<SongDatabase>().songDao() }
    single { HttpClientFactory.create() }

    // Network Stuff
    singleOf(::GeniusScraper)
    singleOf(::GeniusApi)
    singleOf(::LrcLibApi)

    // Repositories and backup stuff
    singleOf(::RushRepository).bind<SongRepo>()

    // Datastore
    single(named("LyricsPage")) { get<DatastoreFactory>().getLyricsPagePreferencesDataStore() }
    single(named("SharePage")) { get<DatastoreFactory>().getSharePagePreferencesDataStore() }
    single(named("Other")) { get<DatastoreFactory>().getOtherPreferencesDataStore() }
    single { OtherPreferencesImpl(get(named("Other"))) }.bind<OtherPreferences>()
    single { LyricsPagePreferencesImpl(get(named("LyricsPage"))) }.bind<LyricsPagePreferences>()
    single { SharePagePreferencesImpl(get(named("SharePage"))) }.bind<SharePagePreferences>()

    // ViewModels
    singleOf(::StateLayer)
    viewModelOf(::SearchSheetVM)
    viewModelOf(::SavedVM)
    viewModelOf(::LyricsVM)
    viewModelOf(::SettingsVM)
    viewModelOf(::ShareVM)
}