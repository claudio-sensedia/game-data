package com.sensedia.gamedata.domain.repository

import com.github.benmanes.caffeine.cache.AsyncLoadingCache
import com.github.benmanes.caffeine.cache.Caffeine
import com.sensedia.gamedata.domain.Match
import com.sensedia.gamedata.domain.Result
import com.sensedia.gamedata.infra.loaders.MatchLoader
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.util.concurrent.CompletableFuture
import java.util.concurrent.TimeUnit

/**
 * @author claudioed on 26/05/18.
 * Project game-data
 */
@Service
class MatchRepository(private val matchLoader: MatchLoader) {

    private val matches: AsyncLoadingCache<String, Match> = Caffeine.newBuilder()
            .maximumSize(100)
            .expireAfterWrite(365, TimeUnit.DAYS)
            .buildAsync({ key -> this.matchLoader.data().associateBy({ it.name }, { it })[key] })

    fun get(name: String) = Mono.fromFuture(this.matches.get(name))

    fun applyResult(name: String, result: Result): Mono<Match> {
        return this.get(name).flatMap {
            updateMatch(it.copy(homeResult = result.homeResult, awayResult = result.awayResult))
        }
    }

    private fun updateMatch(match: Match): Mono<Match> {
        this.matches.put(match.name, CompletableFuture.completedFuture(match))
        return Mono.just(match)
    }

}