package com.example.validations

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import javax.validation.Validation
import javax.validation.Validator

@SpringBootTest
class ValidationsApplicationTests {

	lateinit var validator: Validator

	@BeforeEach
	fun setup() {
		validator = Validation.buildDefaultValidatorFactory().validator
	}

	@Test
	fun contextLoads() {}

	@Test
	fun `should validate child objects`() {
		val dto = MessageDto(
			words = listOf(
				WordDto("Long Word that should fail")
			)
		)

		val violations = validator.validate(dto)

		assertThat(violations).isNotEmpty()
	}
}
