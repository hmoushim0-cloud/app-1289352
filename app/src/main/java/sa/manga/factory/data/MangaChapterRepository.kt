package sa.manga.factory.data

import sa.manga.factory.model.MangaChapter
import sa.manga.factory.model.MangaStory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

interface MangaChapterRepository {
    fun getAvailableCatalog(): Flow<List<MangaStory>>
    fun getActiveRequest(): Flow<MangaChapter?>
    suspend fun submitRequest(request: MangaChapter): Result<String>
}

class InMemoryMangaChapterRepository : MangaChapterRepository {
    private val _catalog = MutableStateFlow<List<MangaStory>>(listOf(
        MangaStory("1", "خدمة تحويل القصة إلى فصل واحد", "تحويل قصة مانجا إلى فصل واحد عالي الجودة مع رسوم احترافية", 5000L, "verified"),
        MangaStory("2", "خدمة تحويل القصة إلى فصلين", "تحويل قصة مانجا إلى فصلين مع رسوم مميزة وتأثيرات بصرية", 9500L, "bolt")
    ))
    private val _active = MutableStateFlow<MangaChapter?>(null)

    override fun getAvailableCatalog(): Flow<List<MangaStory>> = _catalog.asStateFlow()
    override fun getActiveRequest(): Flow<MangaChapter?> = _active.asStateFlow()

    override suspend fun submitRequest(request: MangaChapter): Result<String> {
        _active.value = request
        return Result.success(request.requestId)
    }
}
