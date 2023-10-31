package io.icednut.spring.exercise.config

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

interface TxScope {

    fun <T> writable(block: () -> T): T

    fun <T> readable(block: () -> T): T
}


@Component
class ProductionTxScope : TxScope {

    @Transactional
    override fun <T> writable(block: () -> T): T {
        return block()
    }

    @Transactional(readOnly = true)
    override fun <T> readable(block: () -> T): T {
        return block()
    }
}