package com.sensedia.gamedata.domain.repository

import com.github.benmanes.caffeine.cache.AsyncLoadingCache
import com.github.benmanes.caffeine.cache.Caffeine
import com.sensedia.gamedata.domain.Team
import com.sensedia.gamedata.infra.loaders.TeamLoader
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.util.concurrent.TimeUnit

/**
 * @author claudioed on 24/05/18.
 * Project game-data
 */
@Service
class TeamRepository(val dataLoader: TeamLoader) {

    private val teams: AsyncLoadingCache<String, Team> = Caffeine.newBuilder()
            .maximumSize(100)
            .expireAfterWrite(365, TimeUnit.DAYS)
            .buildAsync( { key -> this.dataLoader.data().associateBy({it.id}, {it})[key]})

    fun get(id:String): Mono<Team> = Mono.fromFuture(this.teams.get(id))

}