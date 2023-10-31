package io.icednut.spring.exercise.controller

import io.icednut.spring.exercise.config.TxConfig
import io.icednut.spring.exercise.repository.User
import io.icednut.spring.exercise.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/user")
class UserController(
    val userService: UserService
) {

    @GetMapping
    fun retrieveUser(userId: Long): Optional<User> {
        return with(TxConfig.txScope) {
            userService.selectUser(userId)
        }
    }

    @PostMapping
    fun createUser(user: User): User {
        return with(TxConfig.txScope) {
            userService.createUser(user)
        }
    }
}