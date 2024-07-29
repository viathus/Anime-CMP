package features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import core.data.repository.AnimeRepository
import core.domain.CalculateRatingUseCase
import features.shareduistate.AnimeUiState
import features.shareduistate.RatingUiState
import io.github.aakira.napier.Napier
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val animeRepository: AnimeRepository,
    private val calculateRatingUseCase: CalculateRatingUseCase,
) : ViewModel() {

    private val _anime = MutableStateFlow(HomeUiState())
    val anime: StateFlow<HomeUiState>
        get() = _anime

    init {
        getAnime()
    }

    private fun getAnime() = viewModelScope.launch(Dispatchers.IO) {
        try {
            _anime.update { it.copy(isLoading = true) }
            
            val anime = animeRepository.getAnimeByPage(1).map {
                val rating = calculateRatingUseCase(it.rating.toDouble())
                AnimeUiState(
                    id = it.id,
                    animeName = it.animeName,
                    posterImage = it.posterImage,
                    rating = RatingUiState(
                        rating = rating.rating,
                        filledStars = rating.filledStars,
                        emptyStars = rating.emptyStars,
                        halfStar = rating.halfStar,
                    ),
                )
            }

            // chunking list to demo homepage
            val listOfAnime = anime.chunked(6)

            _anime.update {
                it.copy(
                    mostPopular = listOfAnime[0] + listOfAnime[3],
                    trending = listOfAnime[1].shuffled(),
                    upcoming = listOfAnime[2].shuffled(),
                )
            }
        } catch (e: Exception) {
            Napier.e("...", e)
        } finally {
            _anime.update { it.copy(isLoading = false) }
        }
    }
}