package pro.lonelywolf.example.gateway

import mu.KotlinLogging
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.gateway.filter.GatewayFilterChain
import org.springframework.cloud.gateway.filter.GlobalFilter
import org.springframework.core.Ordered
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono


@SpringBootApplication
class GatewayApplication

fun main(args: Array<String>) {
  runApplication<GatewayApplication>(*args)
}

@Component
class CustomGlobalFilter : GlobalFilter, Ordered {
  private val log = KotlinLogging.logger {}

  override fun filter(exchange: ServerWebExchange, chain: GatewayFilterChain): Mono<Void> {
    log.info{ "${exchange.request.method} ${exchange.request.path}" }
    return chain.filter(exchange)
  }

  override fun getOrder(): Int {
    return -1
  }
}