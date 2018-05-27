package com.sensedia.gamedata.domain.resource

import com.sensedia.gamedata.domain.service.TeamService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author claudioed on 26/05/18.
 * Project game-data
 */
@RestController
@RequestMapping("/api/teams")
class TeamResource(private val teamService: TeamService) {

    @GetMapping("/{id}")
    fun get(@PathVariable("id")id:String)= this.teamService.get(id)

}