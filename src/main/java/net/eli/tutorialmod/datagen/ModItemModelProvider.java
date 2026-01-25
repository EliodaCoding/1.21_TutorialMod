package net.eli.tutorialmod.datagen;

import net.eli.tutorialmod.TutorialMod;
import net.eli.tutorialmod.blocks.ModBlocks;
import net.eli.tutorialmod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, TutorialMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.CERVALITE.get());
        basicItem(ModItems.RAW_CERVALITE.get());

        basicItem(ModItems.CHISEL.get());
        basicItem(ModItems.STRAWBERRY.get());
        basicItem(ModItems.SPIRIT_ASHES.get());

        buttonItem(ModBlocks.CERVALITE_BUTTON, ModBlocks.CERVALITE_BLOCK);
        fenceItem(ModBlocks.CERVALITE_FENCE, ModBlocks.CERVALITE_BLOCK);
        wallItem(ModBlocks.CERVALITE_WALL, ModBlocks.CERVALITE_BLOCK);

        // Doors typically use an item texture like assets/.../textures/item/<name>.png.
        // We don't have tutorialmod:textures/item/cervalite_door.png, so point the item model
        // at an existing texture to prevent "Texture ... does not exist" crashes.
        withExistingParent(ModBlocks.CERVALITE_DOOR.getId().getPath(), mcLoc("item/generated"))
                .texture("layer0", modLoc("block/cervalite_door_bottom"));
    }

    public void buttonItem(RegistryObject<? extends Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/button_inventory"))
                .texture("texture",  ResourceLocation.fromNamespaceAndPath(TutorialMod.MOD_ID,
                        "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    public void fenceItem(RegistryObject<? extends Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/fence_inventory"))
                .texture("texture",  ResourceLocation.fromNamespaceAndPath(TutorialMod.MOD_ID,
                        "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    public void wallItem(RegistryObject<? extends Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/wall_inventory"))
                .texture("wall",  ResourceLocation.fromNamespaceAndPath(TutorialMod.MOD_ID,
                        "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    private ItemModelBuilder simpleBlockItem(RegistryObject<? extends Block> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/generated")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(TutorialMod.MOD_ID,"item/" + item.getId().getPath()));
    }
}
