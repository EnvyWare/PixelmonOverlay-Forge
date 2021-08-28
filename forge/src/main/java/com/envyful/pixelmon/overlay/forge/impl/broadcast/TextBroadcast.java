package com.envyful.pixelmon.overlay.forge.impl.broadcast;

import com.envyful.api.forge.player.ForgeEnvyPlayer;
import com.envyful.api.player.EnvyPlayer;
import com.envyful.pixelmon.overlay.api.Broadcast;
import com.envyful.pixelmon.overlay.forge.PixelmonOverlayForge;
import com.google.common.collect.Lists;
import com.pixelmonmod.pixelmon.api.overlay.notice.EnumOverlayLayout;
import com.pixelmonmod.pixelmon.api.overlay.notice.NoticeOverlay;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TextBroadcast implements Broadcast {

    private final long duration;
    private final EnumOverlayLayout layout;
    private final List<String> text;

    public TextBroadcast(long duration, EnumOverlayLayout layout, List<String> text) {
        this.duration = duration;
        this.layout = layout;
        this.text = text;
    }

    @Override
    public void send(EnvyPlayer<?> player) {
        NoticeOverlay.builder().addLines(this.text)
                .setLayout(this.layout)
                .setItemStack(new ItemStack(Items.AIR))
                .sendTo((EntityPlayerMP) player.getParent());

        //TODO: add clearing task
    }

    @Override
    public void send(EnvyPlayer<?>... players) {
        for (EnvyPlayer<?> player : players) {
            this.send(player);
        }
    }

    @Override
    public void sendAll() {
        for (ForgeEnvyPlayer onlinePlayer : PixelmonOverlayForge.getInstance().getPlayerManager().getOnlinePlayers()) {
            this.send(onlinePlayer);
        }
    }

    public static class Builder implements Broadcast.Builder<String> {

        private EnumOverlayLayout layout;
        private long duration;
        private List<String> text;

        @Override
        public Broadcast.Builder<String> layout(EnumOverlayLayout layout) {
            this.layout = layout;
            return this;
        }

        @Override
        public Broadcast.Builder<String> duration(long duration, TimeUnit timeUnit) {
            this.duration = timeUnit.toMillis(duration);
            return this;
        }

        @Override
        public Broadcast.Builder<String> duration(int durationSeconds) {
            return this.duration(durationSeconds, TimeUnit.SECONDS);
        }

        @Override
        public Broadcast.Builder<String> lines(String... lines) {
            this.text = Arrays.asList(lines);
            return this;
        }

        @Override
        public Broadcast.Builder<String> lines(List<String> lines) {
            this.text = lines;
            return this;
        }

        @Override
        public Broadcast.Builder<String> display(String display) {
            this.text = Lists.newArrayList(display);
            return this;
        }

        @Override
        public Broadcast build() {
            return new TextBroadcast(this.duration, this.layout, this.text);
        }
    }
}
