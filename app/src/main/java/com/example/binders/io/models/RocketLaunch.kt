package com.kfilios.assesment.io.models

import com.google.gson.annotations.SerializedName


data class RocketLaunch(
    @SerializedName("flight_number")
    val flightNumber: Int,
    @SerializedName("mission_name")
    val missionName: String,
    @SerializedName("launch_year")
    val launchYear: Int,
    @SerializedName("launch_date_utc")
    val launchDateUTC: String,
    @SerializedName("rocket")
    val rocket: Rocket,
    @SerializedName("details")
    val details: String?,
    @SerializedName("launch_success")
    val launchSuccess: Boolean?,
    @SerializedName("links")
    val links: Links
)

data class Rocket(
    @SerializedName("rocket_id")
    val id: String,
    @SerializedName("rocket_name")
    val name: String,
    @SerializedName("rocket_type")
    val type: String
)

data class Links(
    @SerializedName("mission_patch")
    val missionPatchUrl: String?,
    @SerializedName("article_link")
    val articleUrl: String?
)