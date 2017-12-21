package me.kbrewster.api.utilities.gui.widgets


class WidgetButton(val coord: Pair<Int, Int>,
                   val onPress: () -> Unit): Widget() {

    override fun drawWidget() {
        TODO("not implemented")
    }

    override fun onMousePress(x: Int, y: Int) {

    }
}