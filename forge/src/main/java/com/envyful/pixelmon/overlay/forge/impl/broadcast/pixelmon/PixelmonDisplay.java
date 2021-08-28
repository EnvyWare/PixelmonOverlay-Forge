package com.envyful.pixelmon.overlay.forge.impl.broadcast.pixelmon;

public class PixelmonDisplay {

    private final String spec;
    private final boolean sprite;

    public PixelmonDisplay(String spec, boolean sprite) {
        this.spec = spec;
        this.sprite = sprite;
    }

    public String getSpec() {
        return this.spec;
    }

    public boolean isSprite() {
        return this.sprite;
    }
}
