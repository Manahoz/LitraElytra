package net.manahoz.litra_elytra.fabric;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import eu.midnightdust.lib.config.MidnightConfig;
import net.manahoz.litra_elytra.LitraElytraCommon;

public class LEModMenuInit implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> MidnightConfig.getScreen(parent, LitraElytraCommon.MOD_ID);
    }
}
