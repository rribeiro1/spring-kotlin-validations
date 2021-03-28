package com.example.validations

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid
import javax.validation.constraints.Max
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Size

@SpringBootApplication
class ValidationsApplication

fun main(args: Array<String>) {
	runApplication<ValidationsApplication>(*args)
}

class WordDto(
	@field:Size(max = 10)
	val word: String
)

class MessageDto(
	@field:Valid
	@field:NotEmpty
	val words: List<WordDto>
)

class ResponseDto(
	val message: String
) {
	companion object Factory {
		fun of(message: MessageDto): ResponseDto {
			return ResponseDto(
				message = message.words.first().word
			)
		}
	}
}

@RestController
class Controller {
	@PostMapping("/hello")
	fun hello(@Valid @RequestBody messageDto: MessageDto): ResponseDto {
		return ResponseDto.of(messageDto)
	}
}