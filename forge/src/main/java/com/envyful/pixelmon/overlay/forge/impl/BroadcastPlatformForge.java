package com.envyful.pixelmon.overlay.forge.impl;

import com.envyful.api.player.EnvyPlayer;
import com.envyful.pixelmon.overlay.api.Broadcast;
import com.envyful.pixelmon.overlay.api.BroadcastPlatformFactory;
import com.envyful.pixelmon.overlay.forge.impl.broadcast.ItemBroadcast;
import com.envyful.pixelmon.overlay.forge.impl.broadcast.PixelmonBroadcast;
import com.envyful.pixelmon.overlay.forge.impl.broadcast.TextBroadcast;
import com.pixelmonmod.pixelmon.api.overlay.notice.EnumOverlayLayout;
import com.pixelmonmod.pixelmon.api.pokemon.PokemonSpec;
import net.minecraft.item.ItemStack;

public class BroadcastPlatformForge implements BroadcastPlatformFactory {

    public BroadcastPlatformForge() {}

    @SuppressWarnings("unchecked")
    @Override
    public <A> Broadcast.Builder<A> builder(A a) {
        if (a instanceof ItemStack) {
            return (Broadcast.Builder<A>) new ItemBroadcast.Builder().display((ItemStack) a);
        } else if (a instanceof PokemonSpec) {
            return (Broadcast.Builder<A>) new PixelmonBroadcast.Builder().display((PokemonSpec) a);
        } else if (a instanceof String) {
            return (Broadcast.Builder<A>) new TextBroadcast.Builder().display((String) a);
        } else {
            return null;
        }
    }

    @Override
    public void broadcastText(String text, EnvyPlayer<?> target) {
        this.builder(text).duration(10).layout(EnumOverlayLayout.LEFT).build().send(target);
    }

    @Override
    public void broadcastText(String text, EnvyPlayer<?>... targets) {
        this.builder(text).duration(10).layout(EnumOverlayLayout.LEFT).build().send(targets);
    }

    @Override
    public void broadcastTextToAll(String text) {
        this.builder(text).duration(10).layout(EnumOverlayLayout.LEFT).build().sendAll();
    }
}
