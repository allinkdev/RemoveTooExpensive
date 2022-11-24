package me.allink.removetooexpensive.mixin;

import me.allink.removetooexpensive.RemoveTooExpensive;
import net.minecraft.client.gui.screen.ingame.AnvilScreen;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.AnvilScreenHandler;
import net.minecraft.screen.Property;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AnvilScreen.class)
public class AnvilMixin {

	@Redirect(method = "drawForeground", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/network/ClientPlayerEntity;getAbilities()Lnet/minecraft/entity/player/PlayerAbilities;"))
	public PlayerAbilities onGetAbilities(ClientPlayerEntity instance) {
		return RemoveTooExpensive.abilities;
	}

	@Mixin(AnvilScreenHandler.class)
	public static abstract class AnvilHandlerMixin {


		@Redirect(method = "updateResult", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;getAbilities()Lnet/minecraft/entity/player/PlayerAbilities;"))
		public PlayerAbilities onUpdateResult(PlayerEntity instance) {
			return RemoveTooExpensive.abilities;
		}

		@Redirect(method = "updateResult", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/Property;set(I)V"))
		public void onSetLevelCost(Property instance, int i) {
			instance.set(Math.min(50, i));
		}
	}
}
