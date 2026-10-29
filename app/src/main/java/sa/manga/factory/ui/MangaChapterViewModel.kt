package sa.manga.factory.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import sa.manga.factory.data.MangaChapterRepository
import sa.manga.factory.model.MangaChapter
import sa.manga.factory.model.MangaStory
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MangaChapterViewModel(
    private val repository: MangaChapterRepository
) : ViewModel() {
    val catalog: StateFlow<List<MangaStory>> = repository.getAvailableCatalog()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val activeRequest: StateFlow<MangaChapter?> = repository.getActiveRequest()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    fun submitNewOrder(request: MangaChapter) {
        viewModelScope.launch {
            repository.submitRequest(request)
        }
    }
}
