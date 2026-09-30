package net.manahoz.litra_elytra.mixin;

import net.manahoz.litra_elytra.LitraElytraConfig;
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
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EndCityGenerator.Piece.class)
public abstract class LitraElytraEndCityGeneratorMixin extends SimpleStructurePiece {
    public LitraElytraEndCityGeneratorMixin(StructurePieceType type, int length, StructureTemplateManager structureTemplateManager, Identifier id, String template, StructurePlacementData placementData, BlockPos pos) {
        super(type, length, structureTemplateManager, id, template, placementData, pos);
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
                return;
            } else {
                ItemFrameEntity successFrame = new ItemFrameEntity(world.toServerWorld(), pos, this.placementData.getRotation().rotate(Direction.SOUTH));
                successFrame.setHeldItemStack(
                        new ItemStack(Registries.ITEM.get(Identifier.tryParse(LitraElytraConfig.successItemID))));
                world.spawnEntity(successFrame);
            }
        }
        // If the chance prevents the elytra from spawning, config options are checked for the chosen behavior.
        if (LitraElytraConfig.generationMode == LitraElytraConfig.GenerationMode.EMPTY_FRAME) {
            // With "EMPTY_FRAME", the generator spawns an empty item frame, instead of one with an elytra. Nearly identical to the vanilla code.
            ItemFrameEntity failFrame = new ItemFrameEntity(world.toServerWorld(), pos, this.placementData.getRotation().rotate(Direction.SOUTH));
            failFrame.setHeldItemStack((LitraElytraConfig.replaceFailItem ?
                    new ItemStack(Registries.ITEM.get(Identifier.tryParse(LitraElytraConfig.failItemID))) : ItemStack.EMPTY), false);
            world.spawnEntity(failFrame);
        }
        // Else, with "NO_FRAME", no item frame gets spawned and the structure is generated as is.
        ci.cancel();
    }
}
