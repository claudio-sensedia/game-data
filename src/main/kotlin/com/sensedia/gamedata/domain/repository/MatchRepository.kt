package com.sensedia.gamedata.domain.repository

import com.sensedia.gamedata.domain.Match
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux

/**
 * @author claudioed on 27/05/18.
 * Project game-data
 */
interface MatchRepository:ReactiveCrudRepository<Match,String>{

    fun findByRound(round:String):Flux<Match>

}