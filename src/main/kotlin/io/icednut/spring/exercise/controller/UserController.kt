package io.icednut.spring.exercise.controller

import io.icednut.spring.exercise.repository.User
import io.icednut.spring.exercise.service.UserService
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/user")
class UserController(
    val userService: UserService
) {

    @GetMapping
    fun retrieveUser(userId: Long): Optional<User> {
        return userService.selectUser(userId)
    }

    @PostMapping
    fun createUser(@RequestBody user: User) {
        userService.saveUser(user)
    }
}