package me.kbrewster.api.protocol

enum class Protocols(vararg val versions: String) {
    `47`("1.8", "1.8.1, 1.8.2", "1.8.3", "1.8.4",
            "1.8.5", "1.8.6", "1.8.7", "1.8.8", "1.8.9"),
    `107`("1.9"),
    `335`("1.12");
}