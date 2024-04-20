package net.damonj17.customizable_block_entities.item;

import net.damonj17.customizable_block_entities.CustomizableBlockEntities;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, CustomizableBlockEntities.MOD_ID);

    public static final RegistryObject<Item> FIGURA_MARK = ITEMS.register("figura_mark",
            () -> new Item(new Item.Properties()));
        public static final RegistryObject<Item> FIGURA_WRENCH = ITEMS.register("figura_wrench",
            () -> new Item(new Item.Properties().stacksTo(1)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
