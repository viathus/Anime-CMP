package features.seemore

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import core.data.repository.AnimeRepository
import core.domain.CalculateRatingUseCase
import features.shareduistate.AnimeUiState
import features.shareduistate.RatingUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SeeMoreViewModel(
    private val animeRepository: AnimeRepository,
    private val calculateRatingUseCase: CalculateRatingUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val selectedOption = savedStateHandle.get<String>("seeMoreOptions")
    private var currentPage = 2

    private val _anime = MutableStateFlow(SeeMoreUiState())
    
    val anime: StateFlow<SeeMoreUiState>
        get() = _anime

    init {
        getAnime(currentPage)
    }

    private fun getAnime(page: Int) = viewModelScope.launch(Dispatchers.IO) {
        try {
            _anime.update { it.copy(isLoading = true) }

            val anime = animeRepository.getAllAnime(page).map {
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

            _anime.update {
                it.copy(
                    anime = when (selectedOption) {
                        "MOST_POPULAR" -> anime
                        "UPCOMING", "TRENDING" -> anime.shuffled()
                        else -> anime
                    },
                )
            }

            currentPage++
        } catch (e: Exception) {
            println("test.. $e")
        } finally {
            _anime.update { it.copy(isLoading = false) }
        }
    }


    fun loadMore() {
        getAnime(currentPage)
    }
}