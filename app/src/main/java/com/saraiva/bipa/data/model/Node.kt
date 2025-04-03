package com.saraiva.bipa.data.model

data class NodeModel(
    val publicKey : String,
    val alias : String,
    val channels : Int,
    val capacity : Long,
    val firstSeen : Long,
    val updatedAt : Long,
    val city: Map<String, String>?,
    val country: Map<String, String>?
)