package net.manahoz.litra_elytra.forge;

import net.minecraftforge.fml.common.Mod;

import net.manahoz.litra_elytra.LitraElytraCommon;

@Mod(LitraElytraCommon.MOD_ID)
public final class LitraElytraForge {
    public LitraElytraForge() {
        // Run our common setup.
        LitraElytraCommon.init();
    }
}
