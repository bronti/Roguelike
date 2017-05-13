package ru.spbau.yaveyn.sd.roguelike


import asciiPanel.AsciiFont
import javax.swing.JFrame
import asciiPanel.AsciiPanel


class ApplicationMain : JFrame() {
    private var terminal: AsciiPanel = AsciiPanel(screenWidth, screenHeight + notesHeight, AsciiFont.TALRYTH_15_15)
    private val controller: Controller = Controller { repaint() }

    init {
        add(terminal)
        pack()
        addKeyListener(controller)
        repaint()
    }

    override fun repaint() {
        terminal.clear()
        controller.refreshOutput(terminal)
        super.repaint()
    }
}

fun main(args: Array<String>) {
    val app = ApplicationMain()
    app.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    app.isVisible = true
}