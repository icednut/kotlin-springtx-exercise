package io.icednut.spring.exercise.service

import io.icednut.spring.exercise.config.TxScope
import io.icednut.spring.exercise.repository.User
import io.icednut.spring.exercise.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    val userRepository: UserRepository
) {

    context(TxScope)
    fun selectUser(userId: Long) = readable {
        userRepository.findById(userId)
    }

    context(TxScope)
    fun createUser(user: User): User =
        createUserAndLogging(user)

    context(TxScope)
    private fun createUserAndLogging(user: User): User = writable {
        val createdUser = userRepository.save(user)

        println("created user: $createdUser")
        createdUser
    }
}