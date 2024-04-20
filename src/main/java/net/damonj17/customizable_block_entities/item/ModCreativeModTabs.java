package net.damonj17.customizable_block_entities.item;

import net.damonj17.customizable_block_entities.CustomizableBlockEntities;
import net.damonj17.customizable_block_entities.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CustomizableBlockEntities.MOD_ID);

    public static final RegistryObject<CreativeModeTab> CUBLEN_TAB = CREATIVE_MODE_TABS.register("customizable_block_entities",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.FIGURA_MARK.get()))
                    .title(Component.translatable("creativetab.customizable_block_entities"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.FIGURA_MARK.get());
                        pOutput.accept(ModItems.FIGURA_WRENCH.get());

                        pOutput.accept(ModBlocks.CUSTOMIZABLE_BLOCK.get());

                    })
                    .build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
