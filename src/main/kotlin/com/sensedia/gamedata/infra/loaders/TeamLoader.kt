package com.sensedia.gamedata.infra.loaders

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.module.kotlin.readValue
import com.sensedia.gamedata.domain.Team
import com.sensedia.gamedata.infra.loaders.base.DataLoader
import org.springframework.stereotype.Service

/**
 * @author claudioed on 24/05/18.
 * Project game-data
 */
@Service
class TeamLoader : DataLoader<Team>() {

    override fun node(jsonNode: JsonNode) = jsonNode.get("teams")

    override fun parse(jsonNode: JsonNode): List<Team> {
       return MAPPER.readValue(jsonNode.toString())
    }

}