package me.kbrewster.api.utilities

/**
 * TODO: Format such as
 *  ChatComponentBuilder {
 *      += "Hello"
 *      += TextBuilder {
 *          colour = Colours.GREEN
 *          clickable = true
 *          callback = {
 *              System.out.println("Clicked!")
 *          }
 *          text = "World"
 *      }
 *      += "!"
 *  }
 */
class ChatComponentBuilder() {

    constructor(init: ChatComponentBuilder.() -> Unit) : this() {
        init()
    }

}