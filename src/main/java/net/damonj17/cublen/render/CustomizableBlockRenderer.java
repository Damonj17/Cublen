package net.damonj17.cublen.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.damonj17.cublen.custom.ModCustomizableBlockEntity;
import net.damonj17.cublen.figura.CublenPermissionsPlugin;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.SkullModelBase;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.SkullBlockEntity;
import net.minecraft.world.phys.Vec3;
import org.figuramc.figura.FiguraMod;
import org.figuramc.figura.avatar.Avatar;
import net.damonj17.cublen.figura.ducks.CustomizableBlockRendererAccessor ;

import org.figuramc.figura.lua.api.entity.EntityAPI;
import org.figuramc.figura.lua.api.world.BlockStateAPI;
import org.figuramc.figura.lua.api.world.ItemStackAPI;
import org.figuramc.figura.permissions.Permissions;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

public abstract class CustomizableBlockRenderer implements BlockEntityRenderer<ModCustomizableBlockEntity> {

    @Unique
    private static Avatar avatar;
    @Unique
    private static ModCustomizableBlockEntity block;

    public void render(ModCustomizableBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay, CallbackInfo ci) {
        renderCustomizableBlock(pBlockEntity, pPartialTick, pPoseStack, pBuffer, pPackedLight, pPackedOverlay, ci);
        block = pBlockEntity;
        CustomizableBlockRendererAccessor.setRenderMode(CustomizableBlockRendererAccessor.CustomizableBlockRenderMode.BLOCK);
    }

    private static void renderCustomizableBlock(ModCustomizableBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay, CallbackInfo ci) {
        ModCustomizableBlockEntity localBlock = block;
        block = null;
        ItemStack localItem = CustomizableBlockRendererAccessor.getItem();
        CustomizableBlockRendererAccessor.setItem((ItemStack)null);
        Entity localEntity = CustomizableBlockRendererAccessor.getEntity();
        CustomizableBlockRendererAccessor.setEntity((Entity)null);
        CustomizableBlockRendererAccessor.CustomizableBlockRenderMode localMode = CustomizableBlockRendererAccessor.getRenderMode();
        CustomizableBlockRendererAccessor.setRenderMode(CustomizableBlockRendererAccessor.CustomizableBlockRenderMode.OTHER);
        Avatar localAvatar = avatar;
        avatar = null;
        if (localAvatar != null && localAvatar.permissions.get(CublenPermissionsPlugin.CUBLEN_PERMISSION) != 0) {
            FiguraMod.pushProfiler("figura");
            FiguraMod.pushProfiler(localAvatar);
            FiguraMod.pushProfiler("cublenRender");
            BlockStateAPI b = localBlock == null ? null : new BlockStateAPI(localBlock.getBlockState(), localBlock.getBlockPos());
            ItemStackAPI i = localItem != null ? ItemStackAPI.verify(localItem) : null;
            EntityAPI<?> e = localEntity != null ? EntityAPI.wrap(localEntity) : null;
            String m = localMode.name();
            FiguraMod.pushProfiler(localBlock != null ? localBlock.getBlockPos().toString() : String.valueOf(i));
            FiguraMod.pushProfiler("event");
            boolean bool = localAvatar.skullRenderEvent(Minecraft.getInstance().getFrameTime(), b, i, e, m);
            FiguraMod.popPushProfiler("render");

            Direction direction = null;
            float yaw = 0;
            if (bool || localAvatar.skullRender(pPoseStack, pBuffer, pPackedLight, direction, yaw)) {
                ci.cancel();
            }

            FiguraMod.popProfiler(5);
        }
    }
    
    @Override
    public boolean shouldRenderOffScreen(ModCustomizableBlockEntity blockEntity) {
        Avatar localAvatar = avatar; // avatar pointer incase avatar variable is set during render.
        return localAvatar == null || localAvatar.permissions == null ? BlockEntityRenderer.super.shouldRenderOffScreen(blockEntity) : localAvatar.permissions.get(Permissions.OFFSCREEN_RENDERING) == 1;
    }

    @Override
    public int getViewDistance() {
        return BlockEntityRenderer.super.getViewDistance();
    }

    @Override
    public boolean shouldRender(ModCustomizableBlockEntity pBlockEntity, Vec3 pCameraPos) {
        return BlockEntityRenderer.super.shouldRender(pBlockEntity, pCameraPos);
    }
}
