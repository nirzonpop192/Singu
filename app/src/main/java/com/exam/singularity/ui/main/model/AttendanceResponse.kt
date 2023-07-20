package com.exam.singularity.ui.main.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AttendanceResponse(
    @SerialName("app_message")
    var app_message: String?,
    @SerialName("code")
    var code: Int?,
    @SerialName("data")
    var `data`: AttendanceDataMode?,
    @SerialName("user_message")
    var user_message: String?
) {
    @Serializable
    data class AttendanceDataMode(
        @SerialName("created_at")
        var created_at: String?,
        @SerialName("id")
        var id: Int?,
        @SerialName("latitude")
        var latitude: String?,
        @SerialName("longitude")
        var longitude: String?,
        @SerialName("name")
        var name: String?,
        @SerialName("request_id")
        var request_id: String?,
        @SerialName("uid")
        var uid: String?,
        @SerialName("updated_at")
        var updatedAt: String?
    )
}