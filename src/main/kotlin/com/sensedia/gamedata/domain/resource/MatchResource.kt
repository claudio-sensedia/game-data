package com.sensedia.gamedata.domain.resource

import com.sensedia.gamedata.domain.MatchPlayers
import com.sensedia.gamedata.domain.Result
import com.sensedia.gamedata.domain.service.MatchService
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*

/**
 * @author claudioed on 26/05/18.
 * Project game-data
 */
@RestController
@RequestMapping("/api/matches")
class MatchResource(private val matchService: MatchService) {

    private val log = LoggerFactory.getLogger(MatchResource::class.java)

    @GetMapping
    fun matches() = this.matchService.matches().doOnNext {
        log.info("Found all matches")
    }

    @GetMapping("/{id}")
    fun get(@PathVariable("id") id: String) = this.matchService.get(id).doOnNext {
        log.info("Found match by ID $id . ")
    }

    @PutMapping("/{id}")
    fun result(@PathVariable("id") id: String, @RequestBody result: Result) = this.matchService.changeResult(id, result).doOnNext {
        log.info("Applying match result Match $id Home Team ${result.homeResult} Away Team ${result.awayResult}")
    }

    @PutMapping("/{id}/teams")
    fun players(@PathVariable("id") id: String, @RequestBody players: MatchPlayers) = this.matchService.changePlayers(id, players).doOnNext {
        log.info("Applying match teams Match $id Home Team ${players.homeTeam} Away Team ${players.awayTeam}")
    }

}