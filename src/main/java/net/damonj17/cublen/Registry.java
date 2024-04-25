package net.damonj17.cublen;

import net.damonj17.cublen.custom.ModCustomizableBlock;
import net.damonj17.cublen.custom.ModCustomizableBlockEntity;
import net.damonj17.cublen.block.ModBlocks;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class Registry {
    public static class BlockEntityTypes {
        private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, Cublen.MOD_ID);

        @SuppressWarnings("DataFlowIssue")
        public static final RegistryObject<BlockEntityType<ModCustomizableBlockEntity>> CUSTOMIZABLEBLOCK = BLOCK_ENTITY_TYPES.register("customizableblock",
                () -> BlockEntityType.Builder.of(ModCustomizableBlockEntity::new, ModBlocks.CUSTOMIZABLE_BLOCK.get()).build(null));
    }
    public static void register(IEventBus modEventBus) {
        BlockEntityTypes.BLOCK_ENTITY_TYPES.register(modEventBus);
    }
}