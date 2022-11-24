package me.allink.removetooexpensive;

import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.player.PlayerAbilities;

public class RemoveTooExpensive implements ModInitializer {
	public static final PlayerAbilities abilities = new PlayerAbilities();

	static {
		abilities.creativeMode = true;
	}

	@Override
	public void onInitialize() {

	}
}
