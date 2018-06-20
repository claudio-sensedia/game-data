package com.sensedia.gamedata.domain.resource

import com.sensedia.gamedata.domain.service.TeamService
import org.springframework.web.bind.annotation.*
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
    fun get(@PathVariable("id")id:String, @RequestHeader("x-request-id", required = false) xreq: String,
            @RequestHeader("x-b3-traceid", required = false) xtraceid: String,
            @RequestHeader("x-b3-spanid", required = false) xspanid: String,
            @RequestHeader("x-b3-parentspanid", required = false) xparentspanid: String,
            @RequestHeader("x-b3-sampled", required = false) xsampled: String,
            @RequestHeader("x-b3-flags", required = false) xflags: String,
            @RequestHeader("x-ot-span-context", required = false) xotspan: String)= this.teamService.get(id)
    fun get(@PathVariable("id")id:String,@RequestHeader("api-key") apiKey: String)= this.teamService.get(id).doOnNext {
        log.info("Found team by ID $id . API-KEY $apiKey ")
    }

}