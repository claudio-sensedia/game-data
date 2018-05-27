package com.sensedia.gamedata.domain

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.Id
import javax.validation.constraints.NotEmpty

/**
 * @author claudioed on 24/05/18.
 * Project game-data
 */
data class Stadium(@Id val id: String, val name: String, val city: String, val lat: Long, val lng: Long, val image: String)

data class Team(@Id val id: String, val name: String, val fifaCode: String, val iso2: String, val flag: String, val emoji: String, val emojiString: String)

@JsonIgnoreProperties(ignoreUnknown = true)
data class Match(@Id val name: String, val type: String, @JsonProperty("home_team") val homeTeam: String,
                 @JsonProperty("away_team") val awayTeam: String, @JsonProperty("home_result") val homeResult: String,var round:String = "group",
                 @JsonProperty("away_result") val awayResult: String, val stadium: String, val date: String, val finished: Boolean = false)

data class MatchResult(@NotEmpty val name:String,@NotEmpty  val homeResult: String,@NotEmpty  val awayResult: String)

data class Result(@NotEmpty @JsonProperty("home_result") val homeResult: String,@NotEmpty @JsonProperty("away_result") val awayResult: String)