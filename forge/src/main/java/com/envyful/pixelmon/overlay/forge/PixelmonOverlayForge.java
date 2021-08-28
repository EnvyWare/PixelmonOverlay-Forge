package com.envyful.pixelmon.overlay.forge;

import com.envyful.api.forge.command.ForgeCommandFactory;
import com.envyful.api.forge.concurrency.ForgeTaskBuilder;
import com.envyful.api.forge.player.ForgePlayerManager;
import com.envyful.pixelmon.overlay.api.BroadcastFactory;
import com.envyful.pixelmon.overlay.forge.impl.BroadcastPlatformForge;
import com.envyful.pixelmon.overlay.forge.task.ClearTask;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(
        modid = "pixelmonoverlay",
        name = "PixelmonOverlay Forge",
        version = PixelmonOverlayForge.VERSION,
        acceptableRemoteVersions = "*"
)
public class PixelmonOverlayForge {
    public static final String VERSION = "0.1.0";

    private static PixelmonOverlayForge instance;

    private ForgePlayerManager playerManager = new ForgePlayerManager();
    private ForgeCommandFactory commandFactory = new ForgeCommandFactory();

    @Mod.EventHandler
    public void onServerStarting(FMLPreInitializationEvent event) {
        instance = this;

        BroadcastFactory.setPlatformFactory(new BroadcastPlatformForge());

        new ForgeTaskBuilder()
                .async(true)
                .delay(10L)
                .interval(10L)
                .task(new ClearTask())
                .start();
    }

    @Mod.EventHandler
    public void onServerStarting(FMLServerStartingEvent event) {

    }

    public static PixelmonOverlayForge getInstance() {
        return instance;
    }

    public ForgePlayerManager getPlayerManager() {
        return this.playerManager;
    }
}
