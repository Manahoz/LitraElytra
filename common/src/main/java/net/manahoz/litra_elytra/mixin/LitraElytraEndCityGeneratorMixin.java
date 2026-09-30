package net.manahoz.litra_elytra.mixin;

import net.manahoz.litra_elytra.LitraElytraCommon;
import net.manahoz.litra_elytra.config.LitraElytraConfig;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.structure.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.ServerWorldAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EndCityGenerator.Piece.class)
public abstract class LitraElytraEndCityGeneratorMixin extends SimpleStructurePiece {
    public LitraElytraEndCityGeneratorMixin(StructurePieceType type, int length, StructureTemplateManager structureTemplateManager, Identifier id, String template, StructurePlacementData placementData, BlockPos pos) {
        super(type, length, structureTemplateManager, id, template, placementData, pos);
    }

    @Unique
    private void litra_elytra$addLog(String log) {
        if (LitraElytraConfig.enableModLogging) {
            LitraElytraCommon.LOGGER.info(log);
        }
    }

    // "EndCityGenerator.Piece" is where the 'data/minecraft/structures/end_city/ship.nbt' points to, so that's the part we will alter.

    @Inject(
            method = "handleMetadata",
            at = @At("HEAD"),
            cancellable = true
    )
    private void handleEndShipGeneration(String metadata, BlockPos pos, ServerWorldAccess world, Random random, BlockBox boundingBox, CallbackInfo ci) {
        // If the vanilla method doesn't concern with the elytra, it ignores the alteration.
        if (!metadata.startsWith("Elytra")) {
            return;
        }
        boolean rolledSuccess = random.nextFloat() < LitraElytraConfig.itemSpawnChance;
        // If the chance allows for the elytra to spawn, it passes the alternation.
        if (rolledSuccess) {
            if (!LitraElytraConfig.replaceSuccessItem) {
                litra_elytra$addLog("Item spawn rolled success. Following vanilla behavior.");
                return;
            } else if (LitraElytraConfig.enableAdvancedSettings) {
                ItemFrameEntity successFrame = new ItemFrameEntity(world.toServerWorld(), pos, this.placementData.getRotation().rotate(Direction.SOUTH));
                successFrame.setHeldItemStack(
                        new ItemStack(Registries.ITEM.get(Identifier.tryParse(LitraElytraConfig.successItemID))));
                world.spawnEntity(successFrame);
                litra_elytra$addLog("Item spawn rolled success. Set to replacement: replacing the elytra item with the custom one.");
            }
        }
        // If the chance prevents the elytra from spawning, config options are checked for the chosen behavior.
        if (LitraElytraConfig.generationModeOnFail == LitraElytraConfig.GenerationMode.WITH_FRAME) {
            // With "EMPTY_FRAME", the generator spawns an empty item frame, instead of one with an elytra.
            ItemFrameEntity failFrame = new ItemFrameEntity(world.toServerWorld(), pos, this.placementData.getRotation().rotate(Direction.SOUTH));
            failFrame.setHeldItemStack(((LitraElytraConfig.replaceFailItem && LitraElytraConfig.enableAdvancedSettings) ?
                    new ItemStack(Registries.ITEM.get(Identifier.tryParse(LitraElytraConfig.failItemID))) : ItemStack.EMPTY), false);
            world.spawnEntity(failFrame);
            litra_elytra$addLog(LitraElytraConfig.replaceFailItem ?
                    "Item spawn rolled fail. Set to 'EMPTY_FRAME' with replacement: placing the fail item." :
                    "Item spawn rolled fail. Set to 'EMPTY_FRAME': placing no item."
            );
        } // Otherwise the option must be "NO_FRAME", thus the generator spawns nothing.
        else {
            litra_elytra$addLog("Item spawn rolled fail. Set to 'NO_FRAME': placing nothing.");
            ci.cancel();
        }
        // Else, with "NO_FRAME", no item frame gets spawned and the structure is generated as is.
        ci.cancel();
    }
}
