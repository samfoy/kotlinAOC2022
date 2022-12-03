package io.github.samfoy.aoc2022

sealed interface DomainError

sealed interface UserError : DomainError
data class Unexpected(val description: String, val error: Throwable?) : UserError
