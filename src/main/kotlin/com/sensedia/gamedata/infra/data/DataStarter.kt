package com.sensedia.gamedata.infra.data

import com.sensedia.gamedata.domain.repository.MatchRepository
import com.sensedia.gamedata.domain.repository.StadiumRepository
import com.sensedia.gamedata.domain.repository.TeamRepository
import com.sensedia.gamedata.infra.loaders.MatchLoader
import com.sensedia.gamedata.infra.loaders.StadiumLoader
import com.sensedia.gamedata.infra.loaders.TeamLoader
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

/**
 * @author claudioed on 27/05/18.
 * Project game-data
 */
@Service
class DataStarter(private val teamRepository: TeamRepository,private val stadiumRepository: StadiumRepository,
                  private val matchRepository: MatchRepository,private val teamLoader: TeamLoader,
                  private val stadiumLoader: StadiumLoader,private val matchLoader: MatchLoader): ApplicationListener<ApplicationReadyEvent> {

    override fun onApplicationEvent(p0: ApplicationReadyEvent) {

        this.teamRepository.count().subscribe {
            if(it.toInt() == 0){
                this.teamRepository.saveAll(Flux.fromStream(this.teamLoader.data().stream())).subscribe()
            }
        }

        this.stadiumRepository.count().subscribe{
            if(it.toInt() == 0){
                this.stadiumRepository.saveAll(Flux.fromStream(this.stadiumLoader.data().stream())).subscribe()
            }

        }

        this.matchRepository.count().subscribe {
            if(it.toInt() == 0){
                this.matchRepository.saveAll(Flux.fromStream(this.matchLoader.data().stream())).subscribe()

            }
        }

    }

}