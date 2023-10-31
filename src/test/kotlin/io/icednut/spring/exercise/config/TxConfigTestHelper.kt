package io.icednut.spring.exercise.config

abstract class TxConfigTestHelper {

    init {
        val txConfig = TxConfig()
        txConfig.setTxScope(object : TxScope {
            override fun <T> writable(block: () -> T): T {
                return block()
            }

            override fun <T> readable(block: () -> T): T {
                return block()
            }
        })
    }
}