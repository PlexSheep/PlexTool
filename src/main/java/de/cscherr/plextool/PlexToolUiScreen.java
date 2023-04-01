package de.cscherr.plextool;

import de.cscherr.plextool.mixin.PlexToolUIMixin;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.Monitor;
import net.minecraft.client.util.Window;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PlexToolUiScreen extends Screen {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlexToolUiScreen.class.getSimpleName());
    private static final Text INFO_TEXT = Text.literal("Hello world");
    private final Screen parent;

    public PlexToolUiScreen(String name, Screen parent) {
        super(Text.literal(name));
        this.parent = parent;
        LOGGER.info("Constructed the UI");
    }
    @Override
    protected void init() {
        //assert this.client != null;
        //Window window = this.client.getWindow();
        //Monitor monitor = window.getMonitor();

        this.addDrawable(ButtonWidget.builder(INFO_TEXT, button -> {
            LOGGER.info("Pressed dummy button");
        }).dimensions(this.width / 2 - 100, this.height - 10, 200, 20).build());
        //this.addDrawableChild(ButtonWidget.builder(ScreenTexts.DONE, button -> {
        //    this.client.options.write();
        //    this.apply();
        //    this.client.setScreen(this.parent);
        //}).dimensions(this.width / 2 - 100, this.height - 27, 200, 20).build());
    }

    private void apply() {

    }
}
