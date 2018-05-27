package com.sensedia.gamedata.domain

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.NotEmpty

/**
 * @author claudioed on 24/05/18.
 * Project game-data
 */
data class Stadium(val id: String, val name: String, val city: String, val lat: Long, val lng: Long, val image: String)

data class Team(val id: String, val name: String, val fifaCode: String, val iso2: String, val flag: String, val emoji: String, val emojiString: String)

@JsonIgnoreProperties(ignoreUnknown = true)
data class Match(val name: String, val type: String, @JsonProperty("home_team") val homeTeam: String,
                 @JsonProperty("away_team") val awayTeam: String, @JsonProperty("home_result") val homeResult: String,var round:String = "group",
                 @JsonProperty("away_result") val awayResult: String, val stadium: String, val date: String, val finished: Boolean = false)

data class MatchResult(@NotEmpty val name:String,@NotEmpty  val homeResult: String,@NotEmpty  val awayResult: String)

data class Result(@NotEmpty  val homeResult: String,@NotEmpty  val awayResult: String)