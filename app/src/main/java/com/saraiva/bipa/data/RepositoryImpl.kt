package com.saraiva.bipa.data

import com.saraiva.bipa.core.network.BipaService
import com.saraiva.bipa.data.model.NodeModel
import com.saraiva.bipa.domain.entity.NodeEntity
import com.saraiva.bipa.domain.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val service: BipaService) : Repository {
    override suspend fun getNodes(): List<NodeEntity> {

        return service.getNodes().map {
            NodeEntity(
                publicKey = it.publicKey,
                alias = it.alias,
                channels = it.channels,
                capacity = it.capacity,
                firstSeen = it.firstSeen,
                updatedAt = it.updatedAt,
                city = it.city ?: emptyMap(),
                country = it.country ?: emptyMap()
            )

        }
    }
}