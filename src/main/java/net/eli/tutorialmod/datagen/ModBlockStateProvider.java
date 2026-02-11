package net.eli.tutorialmod.datagen;

import net.eli.tutorialmod.TutorialMod;
import net.eli.tutorialmod.blocks.ModBlocks;
import net.eli.tutorialmod.blocks.custom.CervaliteLampBlock;
import net.eli.tutorialmod.blocks.custom.HoneyBerryBushBlock;
import net.eli.tutorialmod.blocks.custom.StrawberryCropBlock;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Function;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, TutorialMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        //"Normal" Blocks
        blockWithItem(ModBlocks.CERVALITE_BLOCK);
        blockWithItem(ModBlocks.RAW_CERVALITE_BLOCK);

        blockWithItem(ModBlocks.CERVALITE_ORE);
        blockWithItem(ModBlocks.CERVALITE_DEEPSLATE_ORE);

        blockWithItem(ModBlocks.MAGIC_BLOCK);

        //"Normal" Non-Solid blocks
        stairsBlock(ModBlocks.CERVALITE_STAIRS.get(), blockTexture(ModBlocks.CERVALITE_BLOCK.get()));
        slabBlock(ModBlocks.CERVALITE_SLAB.get(), blockTexture(ModBlocks.CERVALITE_BLOCK.get()), blockTexture(ModBlocks.CERVALITE_BLOCK.get()));

        buttonBlock(ModBlocks.CERVALITE_BUTTON.get(), blockTexture(ModBlocks.CERVALITE_BLOCK.get()));
        pressurePlateBlock(ModBlocks.CERVALITE_PRESSURE_PLATE.get(), blockTexture(ModBlocks.CERVALITE_BLOCK.get()));

        fenceBlock(ModBlocks.CERVALITE_FENCE.get(), blockTexture(ModBlocks.CERVALITE_BLOCK.get()));
        fenceGateBlock(ModBlocks.CERVALITE_FENCE_GATE.get(), blockTexture(ModBlocks.CERVALITE_BLOCK.get()));
        wallBlock(ModBlocks.CERVALITE_WALL.get(), blockTexture(ModBlocks.CERVALITE_BLOCK.get()));

        doorBlockWithRenderType(ModBlocks.CERVALITE_DOOR.get(), modLoc("block/cervalite_door_bottom"), modLoc("block/cervalite_door_top"), "cutout");
        trapdoorBlockWithRenderType(ModBlocks.CERVALITE_TRAPDOOR.get(), modLoc("block/cervalite_trapdoor"), true, "cutout");

        blockItem(ModBlocks.CERVALITE_STAIRS);
        blockItem(ModBlocks.CERVALITE_SLAB);
        blockItem(ModBlocks.CERVALITE_PRESSURE_PLATE);
        blockItem(ModBlocks.CERVALITE_FENCE_GATE);
        blockItem(ModBlocks.CERVALITE_TRAPDOOR, "_bottom");

        //Multi-BlockState Blocks
        customLamp();

        makeCrop(((CropBlock) ModBlocks.STRAWBERRY_CROP.get()), "strawberry_crop_stage", "strawberry_crop_stage");
        makeBush(((SweetBerryBushBlock) ModBlocks.HONEY_BERRY_BUSH.get()), "honey_berry_bush_stage", "honey_berry_bush_stage");
    }

    public void makeBush(SweetBerryBushBlock block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> states(state, modelName, textureName);
        getVariantBuilder(block).forAllStates(function);

    }

    private ConfiguredModel[] states(BlockState state, String modelName, String textureName) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().cross(modelName + state.getValue(HoneyBerryBushBlock.AGE),
                ResourceLocation.fromNamespaceAndPath(TutorialMod.MOD_ID, "block/" + textureName + state.getValue(HoneyBerryBushBlock.AGE))).renderType("cutout"));
        return models;
    }

    public void makeCrop(CropBlock block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> states(state, block, modelName, textureName);

        getVariantBuilder(block).forAllStates(function);
    }

    private ConfiguredModel[] states(BlockState state, CropBlock block, String modelName, String textureName) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().crop(modelName + state.getValue(((StrawberryCropBlock) block).getAgeProperty()),
                ResourceLocation.fromNamespaceAndPath(TutorialMod.MOD_ID, "block/" + textureName + state.getValue(((StrawberryCropBlock) block).getAgeProperty()))).renderType("cutout"));

        return models;
    }

    private void customLamp() {
        getVariantBuilder(ModBlocks.CERVALITE_LAMP.get()).forAllStates(state -> {
            if(state.getValue(CervaliteLampBlock.CLICKED)) {
                return new ConfiguredModel[]{new ConfiguredModel(models().cubeAll("cervalite_lamp_on",
                        ResourceLocation.fromNamespaceAndPath(TutorialMod.MOD_ID, "block/" + "cervalite_lamp_on")))};
            } else {
                return new ConfiguredModel[]{new ConfiguredModel(models().cubeAll("cervalite_lamp_off",
                        ResourceLocation.fromNamespaceAndPath(TutorialMod.MOD_ID, "block/" + "cervalite_lamp_off")))};
            }
        });
        simpleBlockItem(ModBlocks.CERVALITE_LAMP.get(), models().cubeAll("cervalite_lamp_on",
                ResourceLocation.fromNamespaceAndPath(TutorialMod.MOD_ID, "block/" + "cervalite_lamp_on")));
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

    private void blockItem(RegistryObject<? extends Block> blockRegistryObject) {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile("tutorialmod:block/" +
                ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath()));
    }

    private void blockItem(RegistryObject<? extends Block> blockRegistryObject, String appendix) {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile("tutorialmod:block/" +
                ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath() + appendix));
    }
}
