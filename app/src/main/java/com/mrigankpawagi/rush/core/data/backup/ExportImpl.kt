package com.mrigankpawagi.rush.core.data.backup

import android.os.Environment
import com.mrigankpawagi.rush.core.data.mappers.toSongSchema
import com.mrigankpawagi.rush.core.domain.SongRepo
import com.mrigankpawagi.rush.core.domain.backup.ExportRepo
import com.mrigankpawagi.rush.core.domain.backup.ExportSchema
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.json.Json
import java.io.File
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

class ExportImpl(
    private val songRepo: SongRepo
): ExportRepo {
    @OptIn(ExperimentalTime::class)
    override suspend fun exportToJson() = withContext(Dispatchers.IO) {
        val songsData = songRepo.getAllSongs().map { it.toSongSchema() }
        val exportFolder = File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
            "Rush"
        )

        if (!exportFolder.exists() || !exportFolder.isDirectory) exportFolder.mkdirs()

        val time =
            Clock.System.now().toLocalDateTime(TimeZone.Companion.UTC).toString().replace(":", "")
                .replace(" ", "")
        val file = File(exportFolder, "Rush-Export-$time.json")

        file.writeText(
            Json.Default.encodeToString(
                ExportSchema(
                    schemaVersion = 3,
                    songs = songsData
                )
            )
        )
    }
}