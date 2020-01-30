package com.kaushikam.example.domain.model

interface UserRepository {
	fun findById(id: Long): User?
	fun findByName(name: String): List<User>
	fun findAll(): List<User>
	fun save(persisted: User)
}