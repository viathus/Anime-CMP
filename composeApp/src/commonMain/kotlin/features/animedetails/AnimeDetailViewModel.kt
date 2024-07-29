package features.animedetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import core.data.repository.AnimeRepository
import core.domain.CalculateRatingUseCase
import features.shareduistate.RatingUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AnimeDetailViewModel(
    private val animeRepository: AnimeRepository,
    private val calculateRatingUseCase: CalculateRatingUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val id = checkNotNull(savedStateHandle.get<String>("id"))

    private val _anime = MutableStateFlow(AnimeDetailUiState())
    val anime: StateFlow<AnimeDetailUiState>
        get() = _anime

    init {
        getAnimeById(id)
    }

    private fun getAnimeById(id: String) {
        try {
            viewModelScope.launch(Dispatchers.IO) {
                val anime = animeRepository.getAnimeById(id)
                val rating = calculateRatingUseCase(anime.rating.toDouble())
                _anime.update {
                    it.copy(
                        id = id,
                        animeName = anime.animeName,
                        posterImage = anime.posterImage,
                        coverImage = anime.coverImage,
                        description = anime.description,
                        youtubeUrl = anime.youtubeLink,
                        rating = RatingUiState(
                            rating = rating.rating,
                            filledStars = rating.filledStars,
                            emptyStars = rating.emptyStars,
                            halfStar = rating.halfStar
                        )
                    )
                }
            }
        } catch (e: Exception) {
            println(e)
        }
    }
}