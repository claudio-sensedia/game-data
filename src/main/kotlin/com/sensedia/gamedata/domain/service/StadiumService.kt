package com.sensedia.gamedata.domain.service

import com.sensedia.gamedata.domain.repository.StadiumRepository
import org.springframework.stereotype.Service

/**
 * @author claudioed on 24/05/18.
 * Project game-data
 */
@Service
class StadiumService(val stadiumRepository: StadiumRepository) {

    fun get(id:String) = this.stadiumRepository.get(id)

}