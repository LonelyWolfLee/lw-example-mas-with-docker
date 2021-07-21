package pro.lonelywolf.example.naver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
@EnableFeignClients
class NaverApplication

fun main(args: Array<String>) {
  runApplication<NaverApplication>(*args)
}

@FeignClient(name = "naver", url = "https://www.naver.com/")
interface NaverClient {
  @GetMapping("")
  fun home(): String
}


@RestController
class NaverController(private val client: NaverClient) {
  @GetMapping("")
  fun home(): ResponseEntity<String> = ResponseEntity.ok(client.home())
}
