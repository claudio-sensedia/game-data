package com.sensedia.gamedata.domain.resource

import com.sensedia.gamedata.domain.service.RoundService
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
@RequestMapping("/api/rounds")
class RoundResource(private val roundService: RoundService) {

    @GetMapping
    fun rounds()= this.roundService.rounds()

}