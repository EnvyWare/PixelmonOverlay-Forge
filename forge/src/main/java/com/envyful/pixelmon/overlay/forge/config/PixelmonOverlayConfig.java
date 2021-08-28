package com.envyful.pixelmon.overlay.forge.config;

import com.envyful.api.config.data.ConfigPath;
import com.envyful.api.config.yaml.AbstractYamlConfig;
import com.envyful.pixelmon.overlay.forge.config.type.ItemConfigData;
import com.envyful.pixelmon.overlay.forge.config.type.PixelmonConfigData;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.pixelmonmod.pixelmon.api.overlay.notice.EnumOverlayLayout;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;

import java.util.List;
import java.util.Map;

@ConfigPath("config/PixelmonOverlayForge/config.yml")
@ConfigSerializable
public class PixelmonOverlayConfig extends AbstractYamlConfig {

    private boolean autoBroadcastsEnabled = true;
    private long autoBroadcastDelaySeconds = 300;
    private Map<String, BroadcastConfig> broadcasts = Maps.newHashMap(ImmutableMap.of(
            "one", new BroadcastConfig(),
            "two", new BroadcastConfig(new ItemConfigData("stone", 0)),
            "three", new BroadcastConfig(new PixelmonConfigData("pikachu", false))
    ));

    public PixelmonOverlayConfig() {
        super();
    }

    public long getAutoBroadcastDelaySeconds() {
        return this.autoBroadcastDelaySeconds;
    }

    public boolean isAutoBroadcastsEnabled() {
        return this.autoBroadcastsEnabled;
    }

    public Map<String, BroadcastConfig> getBroadcasts() {
        return this.broadcasts;
    }

    @ConfigSerializable
    public static class BroadcastConfig {

        private String layoutType = EnumOverlayLayout.LEFT_AND_RIGHT.name();
        private List<String> text = Lists.newArrayList("Line 1", "Line 2", "ETC", "yanno");
        private long durationSeconds = 30;
        private ConfigData<?> configData;


        public BroadcastConfig() {}

        public BroadcastConfig(ConfigData<?> configData) {
            this.configData = configData;
        }

        public String getLayoutType() {
            return this.layoutType;
        }

        public List<String> getText() {
            return this.text;
        }

        public long getDurationSeconds() {
            return this.durationSeconds;
        }

        public ConfigData<?> getConfigData() {
            return this.configData;
        }
    }
}
