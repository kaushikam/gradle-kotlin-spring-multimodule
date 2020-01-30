package com.kaushikam.example.infrastructure.repository.hibernate

import com.kaushikam.example.domain.model.User
import com.kaushikam.example.domain.model.UserRepository
import org.springframework.data.repository.Repository

interface HibernateUserRepository: Repository<User, Long>, UserRepository