package core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import core.database.model.AnimeEntity

@Dao
interface AnimeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnime(anime: List<AnimeEntity>)

    @Query("SELECT * FROM anime WHERE id = :id")
    suspend fun getAnimeById(id: String): AnimeEntity

    @Query("SELECT * FROM anime WHERE page = :page")
    suspend fun getAnimeByPage(page: Int): List<AnimeEntity>

    @Query("SELECT * FROM anime")
    suspend fun getAnimeAllPage(): List<AnimeEntity>
}