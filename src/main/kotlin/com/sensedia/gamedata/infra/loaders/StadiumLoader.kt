package com.sensedia.gamedata.infra.loaders

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.module.kotlin.readValue
import com.sensedia.gamedata.domain.Stadium
import com.sensedia.gamedata.infra.loaders.base.DataLoader
import org.springframework.stereotype.Service

/**
 * @author claudioed on 24/05/18.
 * Project game-data
 */
@Service
class StadiumLoader : DataLoader<Stadium>() {

    override fun node(jsonNode: JsonNode) = jsonNode.get("stadiums")

    override fun parse(jsonNode: JsonNode): List<Stadium> {
        return MAPPER.readValue(jsonNode.toString())
    }

}