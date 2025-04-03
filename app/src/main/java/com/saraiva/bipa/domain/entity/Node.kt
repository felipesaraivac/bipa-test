package com.saraiva.bipa.domain.entity

data class NodeEntity(
    val publicKey : String,
    val alias : String,
    val channels : Int,
    val capacity : Long,
    val firstSeen : Long,
    val updatedAt : Long,
    val city: Map<String, String>,
    val country: Map<String, String>
)