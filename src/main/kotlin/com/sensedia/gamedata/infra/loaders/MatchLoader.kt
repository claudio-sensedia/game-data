package com.sensedia.gamedata.infra.loaders

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.module.kotlin.readValue
import com.sensedia.gamedata.domain.Match
import com.sensedia.gamedata.infra.loaders.base.MultiNodesDataLoader
import org.springframework.stereotype.Service

/**
 * @author claudioed on 24/05/18.
 * Project game-data
 */
@Service
class MatchLoader : MultiNodesDataLoader<Match>() {

    val nodes = mapOf(Pair("groups", listOf("a", "b", "c", "d", "e", "f", "g", "h")), Pair("knockout", listOf("round_16", "round_8", "round_4", "round_2_loser", "round_2")))

    override fun node(node: JsonNode): List<Pair<String, JsonNode>> {
        return nodes.map {
            Pair(it.key, node.get(it.key))
        }
    }

    override fun parse(jsonNode: List<Pair<String, JsonNode>>): List<Match> {
        return nodes.map { (key, value) ->
            jsonNode.filter { it.first == key }.flatMap { pair ->
                value.map { pair.second.get(it) }
            }
        }.flatMap {
            it
        }.map {
            val matches = MAPPER.readValue<List<Match>>(it.get("matches").toString())
            matches.map { match -> match.copy(round = it.get("name").textValue()) }
        }.flatMap {
            it
        }
    }

}