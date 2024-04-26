package net.damonj17.cublen.figura;

import net.minecraftforge.fml.loading.FMLPaths;

import java.nio.file.Path;

public class CublenExpectPlatformImpl {
    /**
     * This is our actual method to {@link CublenExpectPlatform#getConfigDirectory()}.
     */
    public static Path getConfigDirectory() {
        return FMLPaths.CONFIGDIR.get();
    }
}
