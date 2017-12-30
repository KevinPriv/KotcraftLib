package me.kbrewster.api.utilities.io

import com.google.gson.JsonElement
import com.google.gson.JsonParser

fun String.toJson(): JsonElement =
        JsonParser().parse(this)

