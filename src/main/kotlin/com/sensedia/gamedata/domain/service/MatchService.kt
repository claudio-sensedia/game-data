package com.sensedia.gamedata.domain.service

import com.sensedia.gamedata.domain.*
import com.sensedia.gamedata.domain.repository.MatchRepository
import com.sensedia.gamedata.domain.repository.TeamRepository
import com.sensedia.gamedata.infra.rabbit.MessageConf
import com.sensedia.gamedata.infra.rabbit.sender.MessageSender
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

/**
 * @author claudioed on 24/05/18.
 * Project game-data
 */
@Service
class MatchService(private val matchRepository: MatchRepository, private val teamRepository: TeamRepository, private val sender: MessageSender, private val messageConf: MessageConf) {

    fun get(id: String): Mono<MatchData> {
        return this.matchRepository.findById(id).flatMap {
            val home = this.teamRepository.findById(it.homeTeam)
            val away = this.teamRepository.findById(it.awayTeam)
            Mono.zip(home, away, Mono.just(it))
        }.flatMap {
            val home = it.t1
            val away = it.t2
            val match = it.t3
            Mono.just(MatchData(name = match.name, type = match.type, homeTeam = home, awayTeam = away, homeResult = match.homeResult, awayResult = match.awayResult, round = match.round, stadium = match.stadium,
                    date = match.date, finished = match.finished))
        }
    }

    fun matches(): Flux<MatchData> {
        return this.matchRepository.findAll().flatMap {
            val home = this.teamRepository.findById(it.homeTeam)
            val away = this.teamRepository.findById(it.awayTeam)
            Mono.zip(home, away, Mono.just(it))
        }.flatMap {
            val home = it.t1
            val away = it.t2
            val match = it.t3
            Mono.just(MatchData(name = match.name, type = match.type, homeTeam = home, awayTeam = away, homeResult = match.homeResult, awayResult = match.awayResult, round = match.round, stadium = match.stadium,
                    date = match.date, finished = match.finished))
        }

    }

    fun byRound(round: String) = this.matchRepository.findByRound(round)

    fun changeResult(name: String, result: Result): Mono<Match> {
        return this.matchRepository.findById(name).map {
            it.copy(homeResult = result.homeResult, awayResult = result.awayResult,finished = true)
        }.flatMap {
            this.matchRepository.save(it)
        }.flatMap {
            this.sender.send(Triple(this.messageConf.matchExchange, this.messageConf.matchResultQueue, MatchResult(name = it.name, homeResult = it.homeResult, awayResult = it.awayResult)))
            Mono.just(it)
        }
    }

    fun changePlayers(name: String, players: MatchPlayers): Mono<Match> {
        return this.matchRepository.findById(name).map {
            it.copy(homeTeam = players.homeTeam, awayTeam = players.awayTeam,finished = false)
        }.flatMap {
            this.matchRepository.save(it)
        }
    }

}