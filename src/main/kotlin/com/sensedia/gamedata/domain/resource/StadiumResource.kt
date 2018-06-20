package com.sensedia.gamedata.domain.resource

import com.sensedia.gamedata.domain.service.StadiumService
import org.springframework.web.bind.annotation.*

/**
 * @author claudioed on 26/05/18.
 * Project game-data
 */
@RestController
@RequestMapping("/api/stadiums")
class StadiumResource(private val stadiumService: StadiumService) {

    @GetMapping("/{id}")
    fun get(@PathVariable("id")id:String, @RequestHeader("x-request-id", required = false) xreq: String,
            @RequestHeader("x-b3-traceid", required = false) xtraceid: String,
            @RequestHeader("x-b3-spanid", required = false) xspanid: String,
            @RequestHeader("x-b3-parentspanid", required = false) xparentspanid: String,
            @RequestHeader("x-b3-sampled", required = false) xsampled: String,
            @RequestHeader("x-b3-flags", required = false) xflags: String,
            @RequestHeader("x-ot-span-context", required = false) xotspan: String)= this.stadiumService.get(id)

}