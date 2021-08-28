package com.envyful.pixelmon.overlay.forge.config;

import org.spongepowered.configurate.objectmapping.ConfigSerializable;

@ConfigSerializable
public interface ConfigData<A> {

    A build();

}
