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
    fun matches(@RequestHeader("x-request-id", required = false) xreq: String,
                @RequestHeader("x-b3-traceid", required = false) xtraceid: String,
                @RequestHeader("x-b3-spanid", required = false) xspanid: String,
                @RequestHeader("x-b3-parentspanid", required = false) xparentspanid: String,
                @RequestHeader("x-b3-sampled", required = false) xsampled: String,
                @RequestHeader("x-b3-flags", required = false) xflags: String,
                @RequestHeader("x-ot-span-context", required = false) xotspan: String) = this.matchService.matches().doOnNext {
        log.info("Found all matches. HEADERS: $xreq $xtraceid $xspanid $xparentspanid $xsampled $xflags $xotspan")
    }

    @GetMapping("/{id}")
    fun get(@PathVariable("id") id: String, @RequestHeader("x-request-id", required = false) xreq: String,
            @RequestHeader("x-b3-traceid", required = false) xtraceid: String,
            @RequestHeader("x-b3-spanid", required = false) xspanid: String,
            @RequestHeader("x-b3-parentspanid", required = false) xparentspanid: String,
            @RequestHeader("x-b3-sampled", required = false) xsampled: String,
            @RequestHeader("x-b3-flags", required = false) xflags: String,
            @RequestHeader("x-ot-span-context", required = false) xotspan: String,
            @RequestHeader("api-key", required = false) apiKey: String) =
            this.matchService.get(id).doOnNext {
                log.info("Found match by ID $id . API-KEY $apiKey  HEADERS: $xreq $xtraceid $xspanid $xparentspanid $xsampled $xflags $xotspan")
            }

    @PutMapping("/{id}")
    fun result(@PathVariable("id") id: String, @RequestBody result: Result, @RequestHeader("x-request-id", required = false) xreq: String,
               @RequestHeader("x-b3-traceid", required = false) xtraceid: String,
               @RequestHeader("x-b3-spanid", required = false) xspanid: String,
               @RequestHeader("x-b3-parentspanid", required = false) xparentspanid: String,
               @RequestHeader("x-b3-sampled", required = false) xsampled: String,
               @RequestHeader("x-b3-flags", required = false) xflags: String,
               @RequestHeader("x-ot-span-context", required = false) xotspan: String) = this.matchService.changeResult(id, result).doOnNext {
        log.info("Applying match result Match $id Home Team ${result.homeResult} Away Team ${result.awayResult} HEADERS: $xreq $xtraceid $xspanid $xparentspanid $xsampled $xflags $xotspan")
    }

    @PutMapping("/{id}/teams")
    fun players(@PathVariable("id") id: String, @RequestBody players: MatchPlayers) = this.matchService.changePlayers(id, players).doOnNext {
        log.info("Applying match teams Match $id Home Team ${players.homeTeam} Away Team ${players.awayTeam}")
    }

}