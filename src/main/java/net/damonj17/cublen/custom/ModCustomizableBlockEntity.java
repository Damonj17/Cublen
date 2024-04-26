package net.damonj17.cublen.custom;

import net.damonj17.cublen.Registry;
import net.damonj17.cublen.Owner;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.ChatFormatting;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.Nameable;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ContainerOpenersCounter;
import net.minecraft.world.level.block.entity.LidBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ModCustomizableBlockEntity extends SyncedBlockEntity implements Nameable {

    private Component defaultName = null;
    public static final String OWNER_TAG = "Owner";
    public static final String CUSTOM_NAME_TAG = "CustomName";


    private Owner owner;
    private Component customName;

    public ModCustomizableBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(Registry.BlockEntityTypes.CUSTOMIZABLEBLOCK.get(), pPos, pBlockState);
        owner = Owner.none();
        this.defaultName = defaultName;
    }

    public static void appendHoverText(ItemStack stack, BlockGetter level, List<Component> tooltip, TooltipFlag flag) {
        CompoundTag tag = stack.getOrCreateTag();
        if (tag.contains("BlockEntityTag", CompoundTag.TAG_COMPOUND)) {
            CompoundTag blockEntityTag = tag.getCompound("BlockEntityTag");

        }
    }


// Save/Load

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        if (owner.getType() != Owner.Type.NONE)
            tag.put(OWNER_TAG, owner.serializeNBT());
        if (this.hasCustomName()) {
            tag.putString("CustomName", Component.Serializer.toJson(customName));
        }
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        if (tag.contains(OWNER_TAG, CompoundTag.TAG_COMPOUND))
            owner.deserializeNBT(tag.getCompound(OWNER_TAG));
        if (this.hasCustomName()) {
            tag.putString("CustomName", Component.Serializer.toJson(customName));
        }
    }


// Ownership:

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
        setChanged();
    }

    public void setOwner(Player player) {
        setOwner(new Owner(player));
    }

    public void onSetPlacedBy(LivingEntity placer, ItemStack stack) {
        if (!placer.level().isClientSide && placer instanceof Player player) {

            if (this.owner.getType() == Owner.Type.NONE)
                setOwner(player);

            setChanged();
        }
    }


    // Name
    @Nullable
    @Override
    public Component getCustomName() {
        return customName;
    }

    public final void setCustomName(Component name) {
        customName = name;
    }

    @NotNull
    public final Component getName() {
        return this.hasCustomName() ? customName : defaultName;
    }

    @NotNull
    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag updateTag = super.getUpdateTag();

        if (customName != null) {
            updateTag.putString("CustomName", Component.Serializer.toJson(customName));
        }

        return updateTag;
    }
}
