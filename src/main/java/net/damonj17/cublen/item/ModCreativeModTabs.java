package net.damonj17.cublen.item;

import net.damonj17.cublen.Cublen;
import net.damonj17.cublen.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Cublen.MOD_ID);

    public static final RegistryObject<CreativeModeTab> CUBLEN_TAB = CREATIVE_MODE_TABS.register("cublen",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.CUBLEN_MARK.get()))
                    .title(Component.translatable("creativetab.cublen"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.CUBLEN_MARK.get());
                        pOutput.accept(ModItems.CUBLEN_WRENCH.get());

                        pOutput.accept(ModBlocks.CUSTOMIZABLE_BLOCK.get());

                    })
                    .build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
