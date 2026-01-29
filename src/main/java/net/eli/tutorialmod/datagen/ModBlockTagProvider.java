package net.eli.tutorialmod.datagen;

import net.eli.tutorialmod.TutorialMod;
import net.eli.tutorialmod.blocks.ModBlocks;
import net.eli.tutorialmod.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, TutorialMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.CERVALITE_BLOCK.get())
                .add(ModBlocks.RAW_CERVALITE_BLOCK.get())
                .add(ModBlocks.CERVALITE_ORE.get())
                .add(ModBlocks.CERVALITE_DEEPSLATE_ORE.get())
                .add(ModBlocks.MAGIC_BLOCK.get());

        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.CERVALITE_ORE.get());

        tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.CERVALITE_DEEPSLATE_ORE.get());

        tag(BlockTags.FENCES).add(ModBlocks.CERVALITE_FENCE.get());
        tag(BlockTags.FENCE_GATES).add(ModBlocks.CERVALITE_FENCE_GATE.get());
        tag(BlockTags.WALLS).add(ModBlocks.CERVALITE_WALL.get());

        tag(ModTags.Blocks.NEEDS_CERVALITE_TOOL)
                .add(ModBlocks.RAW_CERVALITE_BLOCK.get())
                .add(Blocks.OBSIDIAN)
                .addTag(BlockTags.NEEDS_IRON_TOOL);

        tag(ModTags.Blocks.INCORRECT_FOR_CERVALITE_TOOL)
                .addTag(BlockTags.INCORRECT_FOR_IRON_TOOL)
                .remove(ModTags.Blocks.NEEDS_CERVALITE_TOOL);
    }
}
