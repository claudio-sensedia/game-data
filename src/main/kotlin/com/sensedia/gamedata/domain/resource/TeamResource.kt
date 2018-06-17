package com.sensedia.gamedata.domain.resource

import com.sensedia.gamedata.domain.service.TeamService
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*

/**
 * @author claudioed on 26/05/18.
 * Project game-data
 */
@RestController
@RequestMapping("/api/teams")
class TeamResource(private val teamService: TeamService) {

    private val log = LoggerFactory.getLogger(TeamResource::class.java)

    @GetMapping("/{id}")
    fun get(@PathVariable("id")id:String,@RequestHeader("api-key") apiKey: String)= this.teamService.get(id).doOnNext {
        log.info("Found team by ID $id . API-KEY $apiKey ")
    }

}