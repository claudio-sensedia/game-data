package com.sensedia.gamedata.domain.resource

import com.sensedia.gamedata.domain.Result
import com.sensedia.gamedata.domain.service.MatchService
import org.springframework.web.bind.annotation.*

/**
 * @author claudioed on 26/05/18.
 * Project game-data
 */
@RestController
@RequestMapping("/api/matches")
class MatchResource(private val matchService: MatchService) {

    @GetMapping
    fun select(@RequestParam("round") round: String) = this.matchService.byRound(round)

    @GetMapping("/{id}")
    fun get(@PathVariable("id") id: String) = this.matchService.get(id)

    @PutMapping("/{id}")
    fun result(@PathVariable("id") id: String, @RequestBody result: Result) = this.matchService.changeResult(id, result)

}