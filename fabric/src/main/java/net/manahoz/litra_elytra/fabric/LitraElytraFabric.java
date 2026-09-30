package net.manahoz.litra_elytra.fabric;

import net.fabricmc.api.ModInitializer;

import net.manahoz.litra_elytra.LitraElytraCommon;

public final class LitraElytraFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        // Run our common setup.
        LitraElytraCommon.init();
    }
}
