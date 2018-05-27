package com.sensedia.gamedata.infra.rabbit

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * @author claudioed on 27/05/18.
 * Project game-data
 */
@Configuration
class MessageConfigurationProducer(@Value("\${match.result.queue}") private val queue:String,
                                   @Value("\${match.exchange}") private val exchange:String,
                                   @Value("\${match.result.key}") private val routingKey:String){

    @Bean
    fun messageConf(): MessageConf {
        return MessageConf(this.exchange,this.queue,this.routingKey)
    }

}

data class MessageConf(val matchExchange:String,val matchResultQueue:String,val matchResultKey:String)