package com.saraiva.bipa.core.network

import com.saraiva.bipa.data.model.NodeModel
import org.w3c.dom.Node
import retrofit2.http.GET

interface BipaService {

    @GET("v1/lightning/nodes/rankings/connectivity")
    suspend fun getNodes(): List<NodeModel>
}
