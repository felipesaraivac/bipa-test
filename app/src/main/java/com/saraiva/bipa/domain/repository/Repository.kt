package com.saraiva.bipa.domain.repository

import com.saraiva.bipa.domain.entity.NodeEntity

interface Repository {

    fun getNodes(): List<NodeEntity>
}