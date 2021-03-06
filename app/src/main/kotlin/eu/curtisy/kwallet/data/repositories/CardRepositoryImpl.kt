package eu.curtisy.kwallet.data.repositories

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import com.squareup.sqldelight.runtime.coroutines.mapToOneOrNull
import eu.curtisy.kwallet.data.Card
import eu.curtisy.kwallet.data.CardDetailsQueries
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class CardRepositoryImpl(private val queries: CardDetailsQueries) : CardRepository {
    override suspend fun getAll(): Flow<List<Card>> = withContext(Dispatchers.IO) {
        queries.selectAllCards().asFlow().mapToList()
    }

    override fun insertOrUpdate(card: Card) {
        queries.insertOrUpdateCard(card)
    }

    override fun delete(id: Int) {
        queries.deleteCard(id)
    }
}

class PreviewCardRepositoryImpl() : CardRepository {
    override suspend fun getAll(): Flow<List<Card>> = listOf(listOf<Card>()).asFlow()

    override fun insertOrUpdate(card: Card) {
    }

    override fun delete(id: Int) {
    }
}