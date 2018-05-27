package com.sensedia.gamedata.domain.repository

import com.github.benmanes.caffeine.cache.AsyncLoadingCache
import com.github.benmanes.caffeine.cache.Caffeine
import com.sensedia.gamedata.domain.Stadium
import com.sensedia.gamedata.infra.loaders.StadiumLoader
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.util.concurrent.TimeUnit


/**
 * @author claudioed on 24/05/18.
 * Project game-data
 */
@Service
class StadiumRepository(val dataLoader: StadiumLoader) {

    private val stadiums:AsyncLoadingCache<String, Stadium> = Caffeine.newBuilder()
            .maximumSize(100)
            .expireAfterWrite(365, TimeUnit.DAYS)
            .buildAsync( { key -> this.dataLoader.data().associateBy({it.id}, {it})[key]})

    fun get(id:String):Mono<Stadium> = Mono.fromFuture(this.stadiums.get(id))

}