package de.cscherr.plextool.mixin;

import de.cscherr.plextool.PlexToolUiScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.Monitor;
import net.minecraft.client.util.Window;
import net.minecraft.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(OptionsScreen.class)
public abstract class PlexToolUIMixin extends Screen {
    private static final Text PLEXTOOL_TEXT = Text.literal("PlexTool");
    private static final Logger LOGGER = LoggerFactory.getLogger(PlexToolUIMixin.class.getSimpleName());

    protected PlexToolUIMixin(Text title) {
        super(title);
    }

    @Inject(method = "init", at = @At("RETURN"))
    private void init(CallbackInfo callback) {
        addDrawable(ButtonWidget.builder(Text.literal("PT"), button -> {
            assert client != null;
            client.setScreen(new PlexToolUiScreen(PLEXTOOL_TEXT.getString(), (OptionsScreen)(Object)this));
        }).position(this.width / 2 - 180, this.height / 6 + 120 - 6).size(20, 20).build());
    }
}
