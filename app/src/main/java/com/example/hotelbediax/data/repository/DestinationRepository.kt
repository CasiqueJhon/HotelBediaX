package com.example.hotelbediax.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.hotelbediax.R
import com.example.hotelbediax.data.local.DestinationDao
import com.example.hotelbediax.data.local.DestinationEntity
import javax.inject.Inject

class DestinationRepository @Inject constructor(
    private val destinationDao: DestinationDao,
) {

    fun getPagedDestinations(): Pager<Int, DestinationEntity> {
        return Pager(
            config = PagingConfig(
                pageSize = 8,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { destinationDao.getPagedDestinations() }
        )
    }

    suspend fun getAllDestinations(): List<DestinationEntity> {
        return destinationDao.getAllDestinations()
    }

    suspend fun insertDestination(destination: DestinationEntity) {
        destinationDao.insertDestination(destination)
    }

    suspend fun deleteDestination(destination: DestinationEntity) {
        destinationDao.deleteDestination(destination)
    }

    suspend fun getDestinationById(id: Int): DestinationEntity? {
        return destinationDao.getDestinationById(id)
    }

    /**
     * if there is no data in the local database, you can simulate loading remote data.
     * by inserting a sample destination into the local database.
     * @return a list of destinations mocked.
     */
    suspend fun loadInitialData() {
        val existingDestinations = destinationDao.getAllDestinations()

        val preloadedDestinations = listOf(
            DestinationEntity(name = "Paris", description = "Capital of love", detailedDescription = "Paris is the capital and most populous city of France. Situated on the Seine River, in the north of the country, it is in the centre of the Île-de-France region, also known as the région parisienne, \"Paris Region\". The City of Paris has an area of 105 km² and a population of 2,241,346 (2014 estimate). Since the 19th century, the built-up area of Paris has grown far beyond its administrative borders; together with its suburbs, the whole agglomeration has a population of 10,550,350", location = "France", imageUrl = "https://upload.wikimedia.org/wikipedia/commons/a/a8/Tour_Eiffel_Wikimedia_Commons.jpg"),
            DestinationEntity(name = "New York", description = "The big Apple", detailedDescription = "New York, often called New York City or NYC, is the most populous city in the United States, located at the southern tip of New York State on one of the world's largest natural harbors. The city comprises five boroughs, each coextensive with a respective county. New York is a global center of finance and commerce, culture, technology, entertainment and media, academics and scientific output, the arts and fashion, and, as home to the headquarters of the United Nations, international diplomacy.", location = "USA", imageUrl = "https://i.pinimg.com/564x/92/8a/35/928a35f9b5cd9a061345a963eac4c291.jpg"),
            DestinationEntity(name = "Rome", description = "The eternal city", detailedDescription = "Rome (Italian and Latin: Roma, pronounced [ˈroːma] ⓘ) is the capital city of Italy. It is also the capital of the Lazio region, the centre of the Metropolitan City of Rome Capital, and a special comune (municipality) named Comune di Roma Capitale. With 2,860,009 residents in 1,285 km2 (496.1 sq mi),[2] Rome is the country's most populated comune and the third most populous city in the European Union by population within city limits. The Metropolitan City of Rome, with a population of 4,355,725 residents, is the most populous metropolitan city in Italy.[3] Its metropolitan area is the third-most populous within Italy.[5] Rome is located in the central-western portion of the Italian Peninsula, within Lazio (Latium), along the shores of the Tiber Valley. Vatican City (the smallest country in the world and headquarters of the worldwide Catholic Church under the governance of the Holy See)[6] is an independent country inside the city boundaries of Rome, the only existing example of a country within a city. Rome is often referred to as the City of Seven Hills due to its geographic location, and also as the \"Eternal City\". Rome is generally considered to be the cradle of Western civilization and Western Christian culture, and the centre of the Catholic Church", location = "Italy", imageUrl = "https://i.pinimg.com/564x/4a/5a/43/4a5a43b65778d1747039f49b8e3922e7.jpg"),
            DestinationEntity(name = "Dubai", description = "The skyscrapers city", detailedDescription = "Dubai[a] is the most populous city in the United Arab Emirates and the capital of the Emirate of Dubai, the most populated of the country's seven emirates.[7][8][9] The city has a population of around 3.6 million (as of 2022),[10] more than 90% of which are expatriates.", location = "UAE", imageUrl = "https://i.pinimg.com/564x/36/aa/c2/36aac2e33899187cdbbd6a47616e93a7.jpg"),
            DestinationEntity(name = "Madrid", description = "A lively city", detailedDescription = "Madrid is the capital and most populous city of Spain. The city has almost 3.4 million[10] inhabitants and a metropolitan area population of approximately 7 million. It is the second-largest city in the European Union (EU), and its monocentric metropolitan area is the second-largest in the EU.[2][11][12] The municipality covers 604.3 km2 (233.3 sq mi) geographical area.[13] Madrid lies on the River Manzanares in the central part of the Iberian Peninsula at about 650 meters above mean sea level. The capital city of both Spain and the surrounding autonomous community of Madrid (since 1983),[14]: 44  it is also the political, economic, and cultural centre of the country.[15] The climate of Madrid features hot summers and cool winters. ", location = "Spain", imageUrl = "https://i.pinimg.com/564x/1a/f8/25/1af825533895f041c2f00c97b12c7a84.jpg"),
            DestinationEntity(name = "Margarita Island", description = "The beach paradise", detailedDescription = "Margarita Island (Isla de Margarita, Spanish pronunciation: [maɾɣaˈɾita]) is the largest island in the Venezuelan state of Nueva Esparta, situated off the northeastern coast of the country, in the Caribbean Sea. The capital city of Nueva Esparta, La Asunción, is located on the island. ", location = "Venezuela", imageUrl = "https://i.pinimg.com/564x/e0/19/7f/e0197f354269eeda7d2e4506dfa3eefa.jpg")
        )

        preloadedDestinations.forEach { preloaded ->
            val exists = existingDestinations.any { it.name == preloaded.name && it.location == preloaded.location }
            if (!exists) {
                destinationDao.insertDestination(preloaded)
            }
        }
    }

}

