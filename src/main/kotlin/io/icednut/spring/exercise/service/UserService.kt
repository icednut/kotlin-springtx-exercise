package io.icednut.spring.exercise.service

import io.icednut.spring.exercise.repository.User
import io.icednut.spring.exercise.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.Optional

@Service
class UserService(
    val userRepository: UserRepository
) {

    @Transactional(readOnly = true)
    fun selectUser(userId: Long): Optional<User> {
        return userRepository.findById(userId)
    }

    @Transactional
    fun saveUser(user: User) {
        userRepository.save(user)
    }
}