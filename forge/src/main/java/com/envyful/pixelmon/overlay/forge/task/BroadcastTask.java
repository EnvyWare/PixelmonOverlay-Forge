package com.envyful.pixelmon.overlay.forge.task;

import com.envyful.api.math.UtilRandom;
import com.envyful.pixelmon.overlay.api.BroadcastFactory;
import com.envyful.pixelmon.overlay.forge.PixelmonOverlayForge;
import com.envyful.pixelmon.overlay.forge.config.PixelmonOverlayConfig;
import com.pixelmonmod.pixelmon.api.overlay.notice.EnumOverlayLayout;

import java.util.concurrent.TimeUnit;

public class BroadcastTask implements Runnable {

    private final PixelmonOverlayForge mod;
    private long lastBroadcast = System.currentTimeMillis();

    public BroadcastTask(PixelmonOverlayForge mod) {
        this.mod = mod;
    }

    @Override
    public void run() {
        if (!this.mod.getConfig().isAutoBroadcastsEnabled()) {
            return;
        }

        if (!this.canBroadcastYet()) {
            return;
        }

        this.lastBroadcast = System.currentTimeMillis();
        PixelmonOverlayConfig.BroadcastConfig randomBroadcast = this.getRandomBroadcast();

        BroadcastFactory.builder(randomBroadcast.getConfigData() == null ? "" : randomBroadcast.getConfigData().build())
                .lines(randomBroadcast.getText())
                .duration((int) randomBroadcast.getDurationSeconds())
                .layout(EnumOverlayLayout.valueOf(randomBroadcast.getLayoutType().toUpperCase()))
                .build().sendAll();
    }

    private boolean canBroadcastYet() {
        return (this.lastBroadcast + TimeUnit.SECONDS.toMillis(this.mod.getConfig().getAutoBroadcastDelaySeconds())) <=
                System.currentTimeMillis();
    }

    private PixelmonOverlayConfig.BroadcastConfig getRandomBroadcast() {
        return UtilRandom.getRandomElement(this.mod.getConfig().getBroadcasts().values()
                .toArray(new PixelmonOverlayConfig.BroadcastConfig[0]));
    }
}
