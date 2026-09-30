package net.manahoz.litra_elytra;

import eu.midnightdust.lib.config.MidnightConfig;
import net.manahoz.litra_elytra.config.LitraElytraConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class LitraElytraCommon {
    public static final String MOD_ID = "litra_elytra";
    public static final Logger LOGGER = LoggerFactory.getLogger("Litra Elytra");

    public static void init() {
        MidnightConfig.init(MOD_ID, LitraElytraConfig.class);
    }
}
