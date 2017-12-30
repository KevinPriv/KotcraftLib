package me.kbrewster.api.utilities.gui.widgets


class WidgetButton(val firstPoint: Pair<Int, Int>,
                   val secondPoint: Pair<Int, Int>,
                   var text: String,
                   var colour: Int): Widget() {

    override fun drawWidget() {
        TODO("not implemented")
    }

    override fun onMousePress(x: Int, y: Int) {

    }
}