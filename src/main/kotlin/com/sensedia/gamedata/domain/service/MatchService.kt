package com.sensedia.gamedata.domain.service

import com.sensedia.gamedata.domain.Match
import com.sensedia.gamedata.domain.MatchResult
import com.sensedia.gamedata.domain.Result
import com.sensedia.gamedata.domain.repository.MatchRepository
import com.sensedia.gamedata.infra.rabbit.MessageConf
import com.sensedia.gamedata.infra.rabbit.sender.MessageSender
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

/**
 * @author claudioed on 24/05/18.
 * Project game-data
 */
@Service
class MatchService(private val matchRepository: MatchRepository, private val sender: MessageSender,private val messageConf: MessageConf) {

    fun get(id: String) = this.matchRepository.get(id)

    fun changeResult(name: String, result: Result): Mono<Match> {
        return this.matchRepository.applyResult(name, result).flatMap {
            this.sender.send(Triple(this.messageConf.matchExchange,this.messageConf.matchResultQueue, MatchResult(name = it.name, homeResult = it.homeResult, awayResult = it.awayResult)))
            Mono.just(it)
        }
    }

}