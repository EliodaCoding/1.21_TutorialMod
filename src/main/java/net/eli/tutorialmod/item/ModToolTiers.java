package net.eli.tutorialmod.item;

import net.eli.tutorialmod.blocks.ModBlocks;
import net.eli.tutorialmod.util.ModTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public class ModToolTiers {
    public static final Tier CERVALITE = new ForgeTier(1400, 6, 4f, 20,
            ModTags.Blocks.NEEDS_CERVALITE_TOOL, () -> Ingredient.of(ModItems.CERVALITE.get()),
            ModTags.Blocks.INCORRECT_FOR_CERVALITE_TOOL);
}
