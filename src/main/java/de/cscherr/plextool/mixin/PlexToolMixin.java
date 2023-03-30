package de.cscherr.plextool.mixin;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.slf4j.Logger;

import java.time.Duration;
import de.cscherr.plextool.PlexTool;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.listener.ServerPlayPacketListener;
import net.minecraft.network.packet.Packet;
import java.util.function.BooleanSupplier;
import net.minecraft.util.Identifier;

@Mixin(ClientPlayNetworkHandler.class)
public class PlexToolMixin {
	// 'V' is appended because the return type is void
	// see https://fabricmc.net/wiki/tutorial:mixin_injects
	public final Logger PLEX_LOGGER = PlexTool.LOGGER;
	public boolean log_packets = true;
	@Inject(at = @At("HEAD"), method = "sendPacket()V")
	// Method signature is diffrent, but thats what the mixin library wants according to its errors.
	// I could not find this in the documentation.
	private void sendPacket(Packet<ServerPlayPacketListener> packet, CallbackInfo callInfo) {
		// this works!
		// I am now logging every packet i send! Yay me!
		if log_packets {
			this.PLEX_LOGGER.info("Sending Packet: " + packet.toString());
		}
	}

}