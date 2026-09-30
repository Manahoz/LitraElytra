package net.manahoz.litra_elytra.neoforge;

import net.neoforged.fml.common.Mod;

import net.manahoz.litra_elytra.LitraElytraCommon;

@Mod(LitraElytraCommon.MOD_ID)
public final class LitraElytraForge {
    public LitraElytraForge() {
        // Run our common setup.
        LitraElytraCommon.init();
    }
}
