package com.sensedia.gamedata.domain.service

import com.sensedia.gamedata.domain.repository.TeamRepository
import org.springframework.stereotype.Service

/**
 * @author claudioed on 26/05/18.
 * Project game-data
 */
@Service
class TeamService(private val teamRepository: TeamRepository) {

    fun get(id:String) = this.teamRepository.get(id)

}