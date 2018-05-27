package com.sensedia.gamedata.domain.resource

import com.sensedia.gamedata.domain.service.StadiumService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author claudioed on 26/05/18.
 * Project game-data
 */
@RestController
@RequestMapping("/api/stadiums")
class StadiumResource(private val stadiumService: StadiumService) {

    @GetMapping("/{id}")
    fun get(@PathVariable("id")id:String)= this.stadiumService.get(id)

}