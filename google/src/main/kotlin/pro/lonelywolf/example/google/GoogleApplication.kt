package pro.lonelywolf.example.google

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
@EnableFeignClients
class GoogleApplication

fun main(args: Array<String>) {
  runApplication<GoogleApplication>(*args)
}

@FeignClient(name = "google", url = "https://www.google.com/")
interface GoogleClient {
  @GetMapping("")
  fun home(): String
}

@RestController
class GoogleController(private val client: GoogleClient) {
  @GetMapping("")
  fun home(): ResponseEntity<String> = ResponseEntity.ok(client.home())
}