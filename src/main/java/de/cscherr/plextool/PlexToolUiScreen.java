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
import net.minecraft.client.gui.widget.ButtonWidget;
import org.slf4j.event.Level;

public class PlexToolUiScreen extends Screen {

    private static final Logger LOGGER = PlexTool.LOGGER;
    private static final Text INFO_TEXT = Text.literal("Hello world");
    private final Screen parent;
    private ButtonWidget doneButton;
    private ButtonWidget dummyButton;

    public PlexToolUiScreen(Text name, Screen parent) {
        super(name);
        LOGGER.trace("Constructing the UI, call to super is done");
        this.parent = parent;
        LOGGER.info("Constructed the UI Screen");
    }

    public PlexToolUiScreen(Text name) {
        super(name);
        LOGGER.trace("Constructing the UI, call to super is done");
        this.parent = null;
        LOGGER.info("Constructed the UI Screen without a parent");
    }
    @Override
    protected void init() {
        LOGGER.trace(PlexToolUiScreen.class.getSimpleName() + " init started");

        dummyButton = ButtonWidget.builder(Text.literal("Dummy"), b -> apply()).build();
        doneButton = ButtonWidget.builder(Text.literal("Done"), b -> apply()).build();

        this.addDrawableChild(doneButton);

        LOGGER.info(PlexToolUiScreen.class.getSimpleName() + " init finished");
    }

    private void apply() {
        LOGGER.trace("applying PlexToolUI Options, not implemented");

        // return to the parent screen, should be OptionsScreen
        assert client != null;
        client.setScreen(parent);
        this.close();
    }
}
