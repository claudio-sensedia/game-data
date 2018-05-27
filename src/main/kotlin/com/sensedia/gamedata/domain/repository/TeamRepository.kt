package com.sensedia.gamedata.domain.repository

import com.sensedia.gamedata.domain.Team
import org.springframework.data.repository.reactive.ReactiveCrudRepository

/**
 * @author claudioed on 27/05/18.
 * Project game-data
 */
interface TeamRepository:ReactiveCrudRepository<Team,String>