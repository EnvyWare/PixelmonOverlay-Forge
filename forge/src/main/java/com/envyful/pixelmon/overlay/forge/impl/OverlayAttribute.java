package com.envyful.pixelmon.overlay.forge.impl;

import com.envyful.api.forge.player.ForgeEnvyPlayer;
import com.envyful.api.forge.player.attribute.AbstractForgeAttribute;
import com.envyful.pixelmon.overlay.forge.PixelmonOverlayForge;

public class OverlayAttribute extends AbstractForgeAttribute<PixelmonOverlayForge> {

    private boolean toggled = false;

    public OverlayAttribute(PixelmonOverlayForge manager, ForgeEnvyPlayer parent) {
        super(manager, parent);
    }

    public boolean isToggled() {
        return this.toggled;
    }

    public void setToggled(boolean toggled) {
        this.toggled = toggled;
    }

    @Override
    public void load() {}

    @Override
    public void save() {}
}
