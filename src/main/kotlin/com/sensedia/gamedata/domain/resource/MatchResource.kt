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
    fun matches(@RequestHeader("x-request-id", required = false) xreq: String,
                @RequestHeader("x-b3-traceid", required = false) xtraceid: String,
                @RequestHeader("x-b3-spanid", required = false) xspanid: String,
                @RequestHeader("x-b3-parentspanid", required = false) xparentspanid: String,
                @RequestHeader("x-b3-sampled", required = false) xsampled: String,
                @RequestHeader("x-b3-flags", required = false) xflags: String,
                @RequestHeader("x-ot-span-context", required = false) xotspan: String) = this.matchService.matches()

    @GetMapping("/{id}")
    fun get(@PathVariable("id") id: String, @RequestHeader("x-request-id", required = false) xreq: String,
            @RequestHeader("x-b3-traceid", required = false) xtraceid: String,
            @RequestHeader("x-b3-spanid", required = false) xspanid: String,
            @RequestHeader("x-b3-parentspanid", required = false) xparentspanid: String,
            @RequestHeader("x-b3-sampled", required = false) xsampled: String,
            @RequestHeader("x-b3-flags", required = false) xflags: String,
            @RequestHeader("x-ot-span-context", required = false) xotspan: String) = this.matchService.get(id)

    @PutMapping("/{id}")
    fun result(@PathVariable("id") id: String, @RequestBody result: Result, @RequestHeader("x-request-id", required = false) xreq: String,
               @RequestHeader("x-b3-traceid", required = false) xtraceid: String,
               @RequestHeader("x-b3-spanid", required = false) xspanid: String,
               @RequestHeader("x-b3-parentspanid", required = false) xparentspanid: String,
               @RequestHeader("x-b3-sampled", required = false) xsampled: String,
               @RequestHeader("x-b3-flags", required = false) xflags: String,
               @RequestHeader("x-ot-span-context", required = false) xotspan: String) = this.matchService.changeResult(id, result)

}