package de.cscherr.plextool.mixin;

import net.minecraft.network.ClientConnection;
import net.minecraft.network.PacketCallbacks;
import net.minecraft.network.listener.PacketListener;
import net.minecraft.network.packet.Packet;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/*
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
		if (log_packets) {
			this.PLEX_LOGGER.info(
					"Sending Packet: <Type:" +
							packet.getClass().getName() +
							"\nData: " +
							packet.getClass().chan +
							">"
					);
			this.PLEX_LOGGER.debug("Callbackinfo for package dump: {}", callInfo);
		}
	}

}
*/
@Mixin(ClientConnection.class)
public class PlexToolPackets {

	private static final Logger LOGGER = LoggerFactory.getLogger("PlexToolMixin");
	private static boolean logSent = true;
	private static boolean logReceived = false;

	public void setLogSent(boolean logSent) {
		this.logSent = logSent;
	}

	public void setLogReceived(boolean logReceived) {
		this.logReceived = logReceived;
	}

	public boolean isLogSent() {
		return logSent;
	}

	public boolean isLogReceived() {
		return logReceived;
	}

	/**
	 * Inject to the method that sends packets. Must not be static for some reason
	 *
	 * @param packet the Network packet to be sent
	 * @param callbacks magical fabric mixin stuff
	 * @param ci magical fabric mixin stuff
	 */
	@Inject(method = "sendImmediately", at = @At("HEAD"))
	private void logSentPacket(Packet<?> packet, @Nullable PacketCallbacks callbacks, CallbackInfo ci) {
		if (logSent) {
			LOGGER.info(String.format("Sent Package: %s",
					packet.getClass().getSimpleName()
			));
		}
	}

	/**
	 * Inject to the method that receives and handles packets.
	 * Must be static for some reason
	 *
	 * @param packet the Network packet to be sent
	 * @param listener i dont know what this does
	 * @param ci magical fabric mixin stuff
	 */
	@Inject(method = "handlePacket", at = @At("HEAD"))
	private static void logReceivedPacket(Packet<?> packet, PacketListener listener, CallbackInfo ci) {
		if (logReceived) {
			LOGGER.info(String.format("Received Package: %s",
					packet.getClass().getSimpleName()
			));
		}
	}
}