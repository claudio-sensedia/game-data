package com.sensedia.gamedata.infra.loaders.base

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import java.nio.file.Files
import java.nio.file.Paths

/**
 * @author claudioed on 24/05/18.
 * Project game-data
 */
abstract class DataLoader<T> {

    protected val MAPPER = ObjectMapper().registerModule(KotlinModule())

    abstract fun node(node: JsonNode):JsonNode

    fun data(): List<T> {
        return parse(node(load("data.json")))
    }

    abstract fun parse(jsonNode: JsonNode): List<T>

    private fun load(file: String): JsonNode {
        val path = Paths.get(javaClass.classLoader
                .getResource(file)!!.toURI())
        return MAPPER.readTree(String(Files.readAllBytes(path)))
    }

}