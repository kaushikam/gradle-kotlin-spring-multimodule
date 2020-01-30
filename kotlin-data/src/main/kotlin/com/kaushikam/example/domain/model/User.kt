package com.kaushikam.example.domain.model

import javax.persistence.*

@Entity
@Table(name = "user")
class User (
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	val id: Long? = null,

	@Column(name = "name")
	val name: String
) {
	override fun toString(): String {
		return "User(id=$id, name='$name')"
	}
}