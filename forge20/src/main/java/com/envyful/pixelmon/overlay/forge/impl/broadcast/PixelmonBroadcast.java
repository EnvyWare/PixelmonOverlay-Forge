package com.envyful.pixelmon.overlay.forge.impl.broadcast;

import com.envyful.api.forge.chat.UtilChatColour;
import com.envyful.api.player.EnvyPlayer;
import com.envyful.papi.api.util.UtilPlaceholder;
import com.envyful.pixelmon.overlay.api.Broadcast;
import com.envyful.pixelmon.overlay.forge.impl.broadcast.pixelmon.PixelmonDisplay;
import com.envyful.pixelmon.overlay.forge.task.ClearTask;
import com.google.common.collect.Lists;
import com.pixelmonmod.api.pokemon.PokemonSpecificationProxy;
import com.pixelmonmod.pixelmon.api.overlay.notice.EnumOverlayLayout;
import com.pixelmonmod.pixelmon.api.overlay.notice.NoticeOverlay;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PixelmonBroadcast extends TextBroadcast {

    private final PixelmonDisplay display;

    public PixelmonBroadcast(long duration, EnumOverlayLayout layout, List<String> text, PixelmonDisplay display) {
        super(duration, layout, text);

        this.display = display;
    }

    @Override
    public void send(EnvyPlayer<?> player) {
        List<Component> lines = Lists.newArrayList();
        NoticeOverlay.Builder builder = NoticeOverlay.builder()
                .setLayout(this.layout);

        if (this.display.isSprite()) {
            builder.setPokemonSprite(PokemonSpecificationProxy.create(this.display.getSpec()).get());
        } else {
            builder.setPokemon3D(PokemonSpecificationProxy.create(this.display.getSpec()).get());
        }

        for (String s : this.text) {
            lines.add(UtilChatColour.colour(UtilPlaceholder.replaceIdentifiers((ServerPlayer) player.getParent(), s)));
        }

        builder.setLines(lines);
        ClearTask.updateClearTime(player, System.currentTimeMillis() + this.duration);
        builder.sendTo((ServerPlayer) player.getParent());
    }

    public static class Builder implements Broadcast.Builder<PixelmonDisplay> {

        private EnumOverlayLayout layout;
        private long duration;
        private PixelmonDisplay display;
        private List<String> text;

        @Override
        public Broadcast.Builder<PixelmonDisplay> layout(EnumOverlayLayout layout) {
            this.layout = layout;
            return this;
        }

        @Override
        public Broadcast.Builder<PixelmonDisplay> duration(long duration, TimeUnit timeUnit) {
            this.duration = timeUnit.toMillis(duration);
            return this;
        }

        @Override
        public Broadcast.Builder<PixelmonDisplay> duration(int durationSeconds) {
            return this.duration(durationSeconds, TimeUnit.SECONDS);
        }

        @Override
        public Broadcast.Builder<PixelmonDisplay> lines(String... lines) {
            this.text = Arrays.asList(lines);
            return this;
        }

        @Override
        public Broadcast.Builder<PixelmonDisplay> lines(List<String> lines) {
            this.text = lines;
            return this;
        }

        @Override
        public Broadcast.Builder<PixelmonDisplay> display(PixelmonDisplay display) {
            this.display = display;
            return this;
        }

        @Override
        public Broadcast build() {
            return new PixelmonBroadcast(this.duration, this.layout, this.text, this.display);
        }
    }
}
