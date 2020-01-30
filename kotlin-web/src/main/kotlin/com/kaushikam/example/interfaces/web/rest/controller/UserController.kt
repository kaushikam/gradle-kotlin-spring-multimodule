package com.kaushikam.example.interfaces.web.rest.controller

import com.kaushikam.example.domain.model.User
import com.kaushikam.example.domain.model.UserRepository
import com.kaushikam.example.interfaces.web.rest.facade.UserDTO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class UserController @Autowired constructor(
	private val userRepository: UserRepository
) {

	@PostMapping("/hello/{name}")
	fun saveName(
		@PathVariable("name") name: String
	): ResponseEntity<String> {
		val user = User(name = name)
		userRepository.save(user)
		return ResponseEntity("Hello, $name", HttpStatus.OK)
	}

	@GetMapping("/users")
	fun findAll(): ResponseEntity<List<UserDTO>> {
		val users = userRepository.findAll()
		val listOfUsers = mutableListOf<UserDTO>()
		users.forEach {
			val user = UserDTO(it.id!!, it.name)
			listOfUsers.add(user)
		}
		return ResponseEntity(listOfUsers, HttpStatus.OK)
	}
}