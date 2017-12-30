package me.kbrewster.api.utilities.gui

import me.kbrewster.api.utilities.gui.widgets.Widget
import net.minecraft.client.gui.GuiScreen

abstract class GUIAdapter: GuiScreen() {

    val widgets: kotlin.collections.List<Widget> = emptyList()

}