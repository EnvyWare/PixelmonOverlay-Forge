package com.envyful.pixelmon.overlay.forge.config;

import com.envyful.api.config.data.ConfigPath;
import com.envyful.api.config.yaml.AbstractYamlConfig;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;

@ConfigPath("config/PixelmonOverlayForge/config.yml")
@ConfigSerializable
public class PixelmonOverlayConfig extends AbstractYamlConfig {

    private boolean autoBroadcastsEnabled = true;
    private long autoBroadcastDelaySeconds = 300;

    public PixelmonOverlayConfig() {
        super();
    }

    public long getAutoBroadcastDelaySeconds() {
        return this.autoBroadcastDelaySeconds;
    }

    public boolean isAutoBroadcastsEnabled() {
        return this.autoBroadcastsEnabled;
    }
}
