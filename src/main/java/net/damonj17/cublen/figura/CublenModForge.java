package net.damonj17.cublen.figura;

import net.minecraftforge.fml.common.Mod;
import net.damonj17.cublen.figura.CublenPlugin;

/**
 * A mod class is needed for Forge to load the Plugin
 */
@Mod(CublenPlugin.PLUGIN_ID)
public class CublenModForge {
    public CublenModForge() {
        CublenPlugin.init();
    }
}
