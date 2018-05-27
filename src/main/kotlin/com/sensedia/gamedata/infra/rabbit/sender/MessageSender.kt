package com.sensedia.gamedata.infra.rabbit.sender

import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import java.util.concurrent.CompletableFuture

/**
 * @author claudioed on 27/05/18.
 * Project game-data
 */
@Service
class MessageSender(val template: RabbitTemplate) {

    fun send(message: Triple<String, String, Any>): Mono<Void> {
        return Mono.fromFuture(CompletableFuture.runAsync {
            this.template.convertAndSend(message.first, message.second, message.third)
        })
    }

}