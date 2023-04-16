package de.cscherr.plextool.mixin;

import de.cscherr.plextool.PlexTool;
import de.cscherr.plextool.PlexToolUiScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(OptionsScreen.class)
public abstract class PlexToolUIMixin extends Screen {
    private static final Text PLEXTOOL_TEXT = Text.literal("PlexTool");
    private static final Logger LOGGER = PlexTool.LOGGER;

    protected PlexToolUIMixin(Text title) {
        super(title);
    }

    @Inject(method = "init", at = @At("RETURN"))
    private void init(CallbackInfo callback) {
        ButtonWidget plexToolButton = ButtonWidget.builder(PLEXTOOL_TEXT, button -> {
            LOGGER.info("Opening PlexToolUiScreen");
            assert client != null;
            PlexToolUiScreen plexToolUiScreen = new PlexToolUiScreen(PLEXTOOL_TEXT, (OptionsScreen)(Object)this);
            client.setScreen(plexToolUiScreen);
        }).build();
        this.addDrawable(plexToolButton);

        LOGGER.info(PlexToolUIMixin.class.getSimpleName() + " init finished");
    }
}
