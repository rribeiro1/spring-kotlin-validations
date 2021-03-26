package com.example.validations

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid
import javax.validation.constraints.Max
import javax.validation.constraints.NotEmpty

@SpringBootApplication
class ValidationsApplication

fun main(args: Array<String>) {
	runApplication<ValidationsApplication>(*args)
}

data class WordDto(
	@field:Max(5)
	val word: String
)

data class MessageDto(
	@Valid
	@field:NotEmpty
	val words: List<WordDto>
)

@RestController
class Controller {
	@PostMapping("/hello")
	fun hello(@Valid @RequestBody messageDto: MessageDto) {
		messageDto.words.map(System.out::println)
	}
}