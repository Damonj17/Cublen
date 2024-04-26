package net.damonj17.cublen.figura;

import net.minecraft.client.gui.screens.Screen;
import net.damonj17.cublen.screens.CublenScreen;
import org.figuramc.figura.entries.FiguraScreen;
import org.figuramc.figura.entries.annotations.FiguraScreenPlugin;
import org.figuramc.figura.gui.widgets.PanelSelectorWidget;

/**
 * Cublen Screen Plugin
 *  Annotation required for Forge to Locate and Load the Plugin
 *  Entrypoint in fabric.mod.json: figura_screen
 */
@FiguraScreenPlugin
public class CublenScreensPlugin implements FiguraScreen {
    @Override
    public Screen getScreen(Screen parentScreen) {
        return new CublenScreen(parentScreen);
    }

    @Override
    public PanelSelectorWidget.PanelIcon getPanelIcon() {
        return FiguraScreen.super.getPanelIcon();
    }
}
