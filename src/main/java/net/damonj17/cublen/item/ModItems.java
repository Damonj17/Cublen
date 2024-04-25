package net.damonj17.cublen.item;

import net.damonj17.cublen.Cublen;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Cublen.MOD_ID);

    public static final RegistryObject<Item> CUBLEN_MARK = ITEMS.register("cublen_mark",
            () -> new Item(new Item.Properties()));
        public static final RegistryObject<Item> CUBLEN_WRENCH = ITEMS.register("cublen_wrench",
            () -> new Item(new Item.Properties().stacksTo(1)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
