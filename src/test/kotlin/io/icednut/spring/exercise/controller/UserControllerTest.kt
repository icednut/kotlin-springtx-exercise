package io.icednut.spring.exercise.controller

import io.icednut.spring.exercise.config.TxConfig
import io.icednut.spring.exercise.config.TxConfigTestHelper
import io.icednut.spring.exercise.config.TxScope
import io.icednut.spring.exercise.repository.User
import io.icednut.spring.exercise.repository.UserRepository
import io.icednut.spring.exercise.service.UserService
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.data.domain.Example
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.repository.query.FluentQuery
import java.util.*
import java.util.function.Function

class UserControllerTest : TxConfigTestHelper() {

    private val mockUserRepository = object : UserRepository {
        override fun <S : User?> save(entity: S): S {
            return entity
        }

        override fun <S : User?> saveAll(entities: MutableIterable<S>): MutableList<S> {
            TODO("Not yet implemented")
        }

        override fun <S : User?> findAll(example: Example<S>): MutableList<S> {
            TODO("Not yet implemented")
        }

        override fun <S : User?> findAll(example: Example<S>, sort: Sort): MutableList<S> {
            TODO("Not yet implemented")
        }

        override fun findAll(): MutableList<User> {
            TODO("Not yet implemented")
        }

        override fun findAll(sort: Sort): MutableList<User> {
            TODO("Not yet implemented")
        }

        override fun findAll(pageable: Pageable): Page<User> {
            TODO("Not yet implemented")
        }

        override fun <S : User?> findAll(example: Example<S>, pageable: Pageable): Page<S> {
            TODO("Not yet implemented")
        }

        override fun findAllById(ids: MutableIterable<Long>): MutableList<User> {
            TODO("Not yet implemented")
        }

        override fun count(): Long {
            TODO("Not yet implemented")
        }

        override fun <S : User?> count(example: Example<S>): Long {
            TODO("Not yet implemented")
        }

        override fun delete(entity: User) {
            TODO("Not yet implemented")
        }

        override fun deleteAllById(ids: MutableIterable<Long>) {
            TODO("Not yet implemented")
        }

        override fun deleteAll(entities: MutableIterable<User>) {
            TODO("Not yet implemented")
        }

        override fun deleteAll() {
            TODO("Not yet implemented")
        }

        override fun <S : User?> findOne(example: Example<S>): Optional<S> {
            TODO("Not yet implemented")
        }

        override fun <S : User?> exists(example: Example<S>): Boolean {
            TODO("Not yet implemented")
        }

        override fun <S : User?, R : Any?> findBy(
            example: Example<S>,
            queryFunction: Function<FluentQuery.FetchableFluentQuery<S>, R>
        ): R {
            TODO("Not yet implemented")
        }

        override fun flush() {
            TODO("Not yet implemented")
        }

        override fun <S : User?> saveAndFlush(entity: S): S {
            TODO("Not yet implemented")
        }

        override fun <S : User?> saveAllAndFlush(entities: MutableIterable<S>): MutableList<S> {
            TODO("Not yet implemented")
        }

        override fun deleteAllInBatch(entities: MutableIterable<User>) {
            TODO("Not yet implemented")
        }

        override fun deleteAllInBatch() {
            TODO("Not yet implemented")
        }

        override fun deleteAllByIdInBatch(ids: MutableIterable<Long>) {
            TODO("Not yet implemented")
        }

        override fun getReferenceById(id: Long): User {
            TODO("Not yet implemented")
        }

        override fun getById(id: Long): User {
            TODO("Not yet implemented")
        }

        override fun getOne(id: Long): User {
            TODO("Not yet implemented")
        }

        override fun deleteById(id: Long) {
            TODO("Not yet implemented")
        }

        override fun existsById(id: Long): Boolean {
            TODO("Not yet implemented")
        }

        override fun findById(id: Long): Optional<User> {
            return when(id) {
                1L -> Optional.of(User(1L, "Will"))
                else -> Optional.empty()
            }
        }
    }
    private val mockUserService = UserService(mockUserRepository)

    @Test
    fun `사용자를 생성할 수 있다`() {
        // given
        val txScope1 = TxConfig.txScope
        println("[DEBUG] 변경 전 TxConfig.txScope == $txScope1")

        val userController = UserController(mockUserService)

        // when
        val user = userController.createUser(User("Will"))

        val txConfig = TxConfig()
        val newTxScope = object : TxScope {
            override fun <T> writable(block: () -> T): T {
                println("hi")
                return block()
            }

            override fun <T> readable(block: () -> T): T {
                println("ho")
                return block()
            }
        }
        println("[DEBUG] 변경할 txScope == $newTxScope")
        txConfig.setTxScope(newTxScope)

        val txScope2 = TxConfig.txScope // _txScope가 교체 되었을까? (lazy delegate로 인해 안바뀌어야 됨)
        println("[DEBUG] 변경 후 TxConfig.txScope == $txScope2")

        val user2 = userController.createUser(User("Tom"))

        // then
        assertEquals("Will", user.name)
        assertEquals("Tom", user2.name)
    }

    @Test
    fun `사용자를 조회할 수 있다`() {
        // given
        val userController = UserController(mockUserService)

        // when
        val user = userController.retrieveUser(1L)

        // then
        assertEquals("Will", user.get().name)
    }
}