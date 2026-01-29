package net.eli.tutorialmod.item;

import net.eli.tutorialmod.TutorialMod;
import net.eli.tutorialmod.item.custom.ChiselItem;
import net.eli.tutorialmod.item.custom.FuelItem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, TutorialMod.MOD_ID);


    public static final RegistryObject<Item> CERVALITE = ITEMS.register("cervalite",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RAW_CERVALITE = ITEMS.register("raw_cervalite",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CHISEL = ITEMS.register("chisel",
            () -> new ChiselItem(new Item.Properties().durability(64)));

    public static final RegistryObject<Item> STRAWBERRY = ITEMS.register("strawberry",
            () -> new Item(new Item.Properties().food(ModFoodProperties.STRAWBERRY)){
                @Override
                public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
                    pTooltipComponents.add(Component.translatable("tooltip.tutorial.strawberry"));
                    super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
                }
            });
    public static final RegistryObject<Item> SPIRIT_ASHES = ITEMS.register("spirit_ashes",
            () -> new FuelItem(new Item.Properties(), 1200));

    public static final RegistryObject<Item> CERVALITE_SWORD = ITEMS.register("cervalite_sword",
            () -> new SwordItem(ModToolTiers.CERVALITE, new Item.Properties()
                    .attributes(SwordItem.createAttributes(ModToolTiers.CERVALITE, 3, -2.4f))));

    public static final RegistryObject<Item> CERVALITE_PICKAXE = ITEMS.register("cervalite_pickaxe",
            () -> new PickaxeItem(ModToolTiers.CERVALITE, new Item.Properties()
                    .attributes(PickaxeItem.createAttributes(ModToolTiers.CERVALITE, 1, -2.8f))));

    public static final RegistryObject<Item> CERVALITE_SHOVEL = ITEMS.register("cervalite_shovel",
            () -> new ShovelItem(ModToolTiers.CERVALITE, new Item.Properties()
                    .attributes(ShovelItem.createAttributes(ModToolTiers.CERVALITE, 1.5f, -3.0f))));

    public static final RegistryObject<Item> CERVALITE_AXE = ITEMS.register("cervalite_axe",
            () -> new AxeItem(ModToolTiers.CERVALITE, new Item.Properties()
                    .attributes(AxeItem.createAttributes(ModToolTiers.CERVALITE, 6, -3.2f))));

    public static final RegistryObject<Item> CERVALITE_HOE = ITEMS.register("cervalite_hoe",
            () -> new HoeItem(ModToolTiers.CERVALITE, new Item.Properties()
                    .attributes(HoeItem.createAttributes(ModToolTiers.CERVALITE, 0, -3.0f))));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
