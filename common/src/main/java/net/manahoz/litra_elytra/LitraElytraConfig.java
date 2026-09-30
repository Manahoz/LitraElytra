package net.manahoz.litra_elytra;

import eu.midnightdust.lib.config.MidnightConfig;

public class LitraElytraConfig extends MidnightConfig {
    private static final String ID = LitraElytraCommon.MOD_ID;

    @Entry(isSlider = true, min=0f, max=1f) public static float itemSpawnChance = 0.5F;
    @Entry public static GenerationMode generationMode = GenerationMode.EMPTY_FRAME;
    public enum GenerationMode {
        EMPTY_FRAME, NO_FRAME
    }


    @Comment()
    public static Comment spacerMain;


    @Entry
    public static boolean enableAdvancedSettings = false;

    @Comment(name = "___________________________")
    public static Comment lineMain;


    @Condition(requiredOption = ID+":enableAdvancedSettings", visibleButLocked = true)
    @Entry
    public static boolean replaceSuccessItem = false;

    @Condition(requiredOption = ID+":enableAdvancedSettings", visibleButLocked = true)
    @Condition(requiredOption = ID+":replaceSuccessItem", visibleButLocked = true)
    @Entry
    public static String successItemID = "";


    @Condition(requiredOption = ID+":enableAdvancedSettings", visibleButLocked = true)
    @Condition(requiredOption = ID+":generationMode", requiredValue = "EMPTY_FRAME", visibleButLocked = true)
    @Entry
    public static boolean replaceFailItem = false;

    @Condition(requiredOption = ID+":enableAdvancedSettings", visibleButLocked = true)
    @Condition(requiredOption = ID+":replaceFailItem", visibleButLocked = true)
    @Condition(requiredOption = ID+":generationMode", requiredValue = "EMPTY_FRAME", visibleButLocked = true)
    @Entry
    public static String failItemID = "";
}
