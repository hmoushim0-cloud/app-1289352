package sa.manga.factory.model

import kotlinx.serialization.Serializable

@Serializable
data class MangaStory(
    val id: String,
    val title: String,
    val description: String,
    val priceMinorUnits: Long,
    val iconVector: String = "verified"
)

@Serializable
data class MangaChapter(
    val requestId: String,
    val items: List<MangaStory>,
    val customerPhone: String,
    val totalAmountMinorUnits: Long,
    val status: MangaChapterStatus = MangaChapterStatus.PENDING,
    val createdAtEpochMs: Long = System.currentTimeMillis()
)

enum class MangaChapterStatus {
    PENDING,
    ACCEPTED,
    IN_PROGRESS,
    COMPLETED,
    CANCELLED
}
