package com.sensedia.gamedata.domain.service

import com.sensedia.gamedata.domain.repository.MatchRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

/**
 * @author claudioed on 27/05/18.
 * Project game-data
 */
@Service
class RoundService(private val matchRepository: MatchRepository) {

    fun rounds(): Mono<MutableSet<String>> {
        return this.matchRepository.findAll().collectMap { it.round }.map { it.keys }
    }

}