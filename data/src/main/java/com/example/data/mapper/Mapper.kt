package com.example.data.mapper

interface Mapper<I, O> {
    fun map(input: I): O
    fun reverseMap(input: O): I
}