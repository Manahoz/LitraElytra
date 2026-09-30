package net.manahoz.litra_elytra.fabric;

import net.fabricmc.api.ModInitializer;

import net.manahoz.litra_elytra.LitraElytraCommon;

public final class LitraElytraFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        LitraElytraCommon.init();
    }
}
