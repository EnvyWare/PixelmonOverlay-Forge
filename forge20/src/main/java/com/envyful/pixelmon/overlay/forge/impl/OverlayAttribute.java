package com.envyful.pixelmon.overlay.forge.impl;

import com.envyful.api.forge.player.ForgePlayerManager;
import com.envyful.api.forge.player.attribute.ManagedForgeAttribute;
import com.envyful.pixelmon.overlay.forge.PixelmonOverlayForge;

public class OverlayAttribute extends ManagedForgeAttribute<PixelmonOverlayForge> {

    private boolean toggled = false;
    private long loginTime = System.currentTimeMillis();

    public OverlayAttribute(ForgePlayerManager playerManager) {
        super(PixelmonOverlayForge.getInstance(), playerManager);
    }

    public boolean isToggled() {
        return this.toggled;
    }

    public void setToggled(boolean toggled) {
        this.toggled = toggled;
    }

    public long getLoginTime() {
        return this.loginTime;
    }

    @Override
    public void load() {}

    @Override
    public void save() {}
}
