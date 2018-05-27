package com.sensedia.gamedata.infra.loaders.base

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import java.nio.file.Files
import java.nio.file.Paths

/**
 * @author claudioed on 24/05/18.
 * Project game-data
 */
abstract class MultiNodesDataLoader<T> {

    protected val MAPPER = ObjectMapper().registerModule(KotlinModule()).configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

    abstract fun node(node: JsonNode):List<Pair<String,JsonNode>>

    fun data(): List<T> {
        return parse(node(load("data.json")))
    }

    abstract fun parse(jsonNode: List<Pair<String,JsonNode>>): List<T>

    private fun load(file: String): JsonNode {
        val path = Paths.get(javaClass.classLoader
                .getResource(file)!!.toURI())
        return MAPPER.readTree(String(Files.readAllBytes(path)))
    }

}