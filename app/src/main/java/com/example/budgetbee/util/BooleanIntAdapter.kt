package com.example.budgetbee.util

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter

class BooleanIntAdapter : TypeAdapter<Boolean>() {
    override fun write(out: JsonWriter, value: Boolean) {
        out.value(if (value) 1 else 0)
    }

    override fun read(`in`: JsonReader): Boolean {
        return when (`in`.peek()) {
            com.google.gson.stream.JsonToken.NUMBER -> `in`.nextInt() != 0
            com.google.gson.stream.JsonToken.BOOLEAN -> `in`.nextBoolean()
            else -> false
        }
    }
}