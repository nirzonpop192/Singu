package com.exam.singularity.ui.main.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StoreResponse(
    @SerialName("data")
    var `data`: List<StoreDataModel>,
    @SerialName("links")
    var links: Links?,
    @SerialName("meta")
    var meta: Meta
) {
    @Serializable
    data class StoreDataModel(
        @SerialName("address")
        var address: String?,
        @SerialName("id")
        var id: Int?,
        @SerialName("name")
        var name: String?
    )

    @Serializable
    data class Links(
        @SerialName("first")
        var first: String?,
        @SerialName("last")
        var last: String?,
        @SerialName("next")
        var next: String?,
        @SerialName("prev")
        var prev: String?
    )

    @Serializable
    data class Meta(
        @SerialName("current_page")
        var current_page: Int?,
        @SerialName("from")
        var from: Int?,
        @SerialName("last_page")
        var last_page: Int?,
        @SerialName("path")
        var path: String?,
        @SerialName("per_page")
        var per_page: Int?,
        @SerialName("to")
        var to: Int?,
        @SerialName("total")
        var total: Int?
    )
}