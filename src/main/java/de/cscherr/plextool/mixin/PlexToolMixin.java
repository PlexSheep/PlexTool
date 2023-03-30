package de.cscherr.plextool.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import de.cscherr.plextool.PlexTool;
import net.minecraft.client.gui.screen.TitleScreen;

@Mixin(TitleScreen.class)
public class PlexToolMixin {
	@Inject(at = @At("HEAD"), method = "init()V")
	private void init(CallbackInfo info) {
		PlexTool.LOGGER.info("This line is printed by my mod mixin!");
	}
}