package com.sensedia.gamedata.infra.loaders.base

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule

/**
 * @author claudioed on 24/05/18.
 * Project game-data
 */
abstract class DataLoader<T> {

    protected val MAPPER = ObjectMapper().registerModule(KotlinModule())

    abstract fun node(node: JsonNode):JsonNode

    fun data(): List<T> {
        return parse(node(load()))
    }

    abstract fun parse(jsonNode: JsonNode): List<T>

    private fun load(): JsonNode {
        return MAPPER.readTree(System.getenv("MATCHES"))
    }

}