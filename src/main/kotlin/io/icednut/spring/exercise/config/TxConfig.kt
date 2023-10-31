package io.icednut.spring.exercise.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration

@Configuration
class TxConfig {

    companion object {
        private lateinit var _txScope: TxScope
        val txScope: TxScope by lazy {
            _txScope
        }
    }

    @Autowired
    fun setTxScope(txScope: TxScope) {
        _txScope = txScope
    }
}