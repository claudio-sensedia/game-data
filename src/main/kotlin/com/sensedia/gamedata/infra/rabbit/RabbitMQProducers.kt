package com.sensedia.gamedata.infra.rabbit

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.TopicExchange
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


/**
 * @author claudioed on 27/05/18.
 * Project game-data
 */
@Configuration
class RabbitMQProducers(private val messageConf: MessageConf) {

    @Bean
    fun queue(): Queue {
        return Queue(this.messageConf.matchResultQueue, true)
    }

    @Bean
    fun exchange(): TopicExchange {
        return TopicExchange(this.messageConf.matchExchange)
    }

    @Bean
    fun binding(queue: Queue, exchange: TopicExchange): Binding {
        return BindingBuilder.bind(queue).to(exchange).with(this.messageConf.matchResultKey)
    }

    @Bean
    fun converter(): Jackson2JsonMessageConverter {
        return Jackson2JsonMessageConverter(ObjectMapper().registerModule(KotlinModule()))
    }

}