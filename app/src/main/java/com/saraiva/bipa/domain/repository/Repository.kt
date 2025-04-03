package com.saraiva.bipa.domain.repository

import com.saraiva.bipa.domain.entity.NodeEntity

interface Repository {

    suspend fun getNodes(): List<NodeEntity>

}