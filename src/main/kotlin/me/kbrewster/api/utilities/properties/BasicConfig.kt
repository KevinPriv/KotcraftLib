package me.kbrewster.api.utilities.properties

import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.util.*

class BasicConfig(val location: File) {

    private val prop: Properties = Properties()

    operator fun plusAssign(config: Pair<String,String>){
        val (key, value) = config
        with(prop) {
            this.load(FileInputStream(location))
            this.setProperty(key, value)
            this.store(FileOutputStream(location), null)
        }
    }

    operator fun get(property: String): String? {
        prop.load(FileInputStream(location))
        return prop.getProperty(property)
    }

}