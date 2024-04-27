package net.damonj17.cublen.figura.ducks;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;

public abstract class CustomizableBlockRendererAccessor {

    private static ItemStack stack = null;
    private static Entity entity = null;
    private static CustomizableBlockRenderMode renderMode = CustomizableBlockRenderMode.OTHER;

    public static void setItem(ItemStack item) {
        stack = item;
    }

    public static ItemStack getItem() {
        return stack;
    }

    public static void setEntity(Entity e) {
        entity = e;
    }

    public static Entity getEntity() {
        return entity;
    }

    public static void setRenderMode(CustomizableBlockRenderMode customizableBlockRenderMode) {
        renderMode = customizableBlockRenderMode;
    }

    public static CustomizableBlockRenderMode getRenderMode() {
        return renderMode;
    }

    public enum CustomizableBlockRenderMode {
        HEAD,
        FIRST_PERSON_LEFT_HAND,
        FIRST_PERSON_RIGHT_HAND,
        THIRD_PERSON_LEFT_HAND,
        THIRD_PERSON_RIGHT_HAND,
        BLOCK,
        OTHER
    }
}