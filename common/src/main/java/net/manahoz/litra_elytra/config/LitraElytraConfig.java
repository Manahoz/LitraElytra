package net.manahoz.litra_elytra.config;

import eu.midnightdust.lib.config.MidnightConfig;
import net.manahoz.litra_elytra.LitraElytraCommon;

import java.nio.file.Path;

public class LitraElytraConfig extends MidnightConfig {

    private static final String ID = LitraElytraCommon.MOD_ID;

    @Condition(requiredModId = "a_non_existent_mod")
    @Entry public static String $_NOTICE_$ =
            "It is highly recommended to refer to the configuration guide in the mod page(s) prior to changing these options. " +
            "If you are a regular player, please use the mods client-side config menu in the game instead of this.";

    @Entry(isSlider = true, min=0f, max=1f) public static float itemSpawnChance = 0.5F;
    @Entry public static GenerationMode generationModeOnFail = GenerationMode.WITH_FRAME;
    public enum GenerationMode {
        WITH_FRAME, NO_FRAME
    }


    @Entry public static boolean enableModLogging = false;


    @Comment() public static Comment spacerMain;


    @Entry public static boolean enableAdvancedSettings = false;

    @Comment(name = "___________________________")
    public static Comment lineMain;


    @Condition(requiredOption = ID+":enableAdvancedSettings", visibleButLocked = true)
    @Entry public static boolean replaceSuccessItem = false;

    @Condition(requiredOption = ID+":enableAdvancedSettings", visibleButLocked = true)
    @Condition(requiredOption = ID+":replaceSuccessItem", visibleButLocked = true)
    @Entry public static String successItemID = "";


    @Condition(requiredOption = ID+":enableAdvancedSettings", visibleButLocked = true)
    @Condition(requiredOption = ID+":generationMode", requiredValue = "EMPTY_FRAME", visibleButLocked = true)
    @Entry public static boolean replaceFailItem = false;

    @Condition(requiredOption = ID+":enableAdvancedSettings", visibleButLocked = true)
    @Condition(requiredOption = ID+":replaceFailItem", visibleButLocked = true)
    @Condition(requiredOption = ID+":generationMode", requiredValue = "EMPTY_FRAME", visibleButLocked = true)
    @Entry public static String failItemID = "";
}
