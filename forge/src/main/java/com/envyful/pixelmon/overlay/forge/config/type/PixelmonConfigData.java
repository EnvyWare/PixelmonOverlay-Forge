package com.envyful.pixelmon.overlay.forge.config.type;

import com.envyful.pixelmon.overlay.forge.config.ConfigData;
import com.envyful.pixelmon.overlay.forge.impl.broadcast.pixelmon.PixelmonDisplay;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;

@ConfigSerializable
public class PixelmonConfigData implements ConfigData<PixelmonDisplay> {

    private String spec;
    private boolean sprite;

    public PixelmonConfigData() {
    }

    public PixelmonConfigData(String spec, boolean sprite) {
        this.spec = spec;
        this.sprite = sprite;
    }

    @Override
    public PixelmonDisplay build() {
        return new PixelmonDisplay(this.spec, this.sprite);
    }
}
