package com.envyful.pixelmon.overlay.forge;

import com.envyful.api.config.yaml.YamlConfigFactory;
import com.envyful.api.forge.command.ForgeCommandFactory;
import com.envyful.api.forge.concurrency.ForgeTaskBuilder;
import com.envyful.api.forge.player.ForgePlayerManager;
import com.envyful.pixelmon.overlay.api.BroadcastFactory;
import com.envyful.pixelmon.overlay.forge.command.OverlayCommand;
import com.envyful.pixelmon.overlay.forge.config.PixelmonOverlayConfig;
import com.envyful.pixelmon.overlay.forge.impl.BroadcastPlatformForge;
import com.envyful.pixelmon.overlay.forge.impl.OverlayAttribute;
import com.envyful.pixelmon.overlay.forge.task.BroadcastTask;
import com.envyful.pixelmon.overlay.forge.task.ClearTask;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;

import java.io.IOException;

@Mod("pixelmonoverlay")
public class PixelmonOverlayForge {

    private static PixelmonOverlayForge instance;

    private PixelmonOverlayConfig config;

    private ForgePlayerManager playerManager = new ForgePlayerManager();
    private ForgeCommandFactory commandFactory = new ForgeCommandFactory();

    public PixelmonOverlayForge() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        instance = this;

        BroadcastFactory.setPlatformFactory(new BroadcastPlatformForge());

        this.loadConfig();

        this.playerManager.registerAttribute(this, OverlayAttribute.class);

        new ForgeTaskBuilder()
                .async(true)
                .delay(10L)
                .interval(10L)
                .task(new ClearTask())
                .start();

        new ForgeTaskBuilder()
                .async(true)
                .delay(20L)
                .interval(20L)
                .task(new BroadcastTask(this))
                .start();
    }

    public void loadConfig() {
        try {
            this.config = YamlConfigFactory.getInstance(PixelmonOverlayConfig.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SubscribeEvent
    public void onServerStarting(RegisterCommandsEvent event) {
        this.commandFactory.registerCommand(event.getDispatcher(), new OverlayCommand());
    }

    public static PixelmonOverlayForge getInstance() {
        return instance;
    }

    public ForgePlayerManager getPlayerManager() {
        return this.playerManager;
    }

    public PixelmonOverlayConfig getConfig() {
        return this.config;
    }
}
