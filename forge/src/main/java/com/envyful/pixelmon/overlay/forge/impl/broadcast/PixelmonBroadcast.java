package com.envyful.pixelmon.overlay.forge.impl.broadcast;

import com.envyful.api.player.EnvyPlayer;
import com.envyful.pixelmon.overlay.api.Broadcast;
import com.pixelmonmod.pixelmon.api.overlay.notice.EnumOverlayLayout;
import com.pixelmonmod.pixelmon.api.overlay.notice.NoticeOverlay;
import com.pixelmonmod.pixelmon.api.pokemon.PokemonSpec;
import net.minecraft.entity.player.EntityPlayerMP;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PixelmonBroadcast extends TextBroadcast {

    private final PokemonSpec display;

    public PixelmonBroadcast(long duration, EnumOverlayLayout layout, List<String> text, PokemonSpec display) {
        super(duration, layout, text);

        this.display = display;
    }

    @Override
    public void send(EnvyPlayer<?> player) {
        NoticeOverlay.builder().addLines(this.text)
                .setLayout(this.layout)
                .setPokemon3D(this.display)
                .sendTo((EntityPlayerMP) player.getParent());

        //TODO: add clearing task
    }

    public static class Builder implements Broadcast.Builder<PokemonSpec> {

        private EnumOverlayLayout layout;
        private long duration;
        private PokemonSpec display;
        private List<String> text;

        @Override
        public Broadcast.Builder<PokemonSpec> layout(EnumOverlayLayout layout) {
            this.layout = layout;
            return this;
        }

        @Override
        public Broadcast.Builder<PokemonSpec> duration(long duration, TimeUnit timeUnit) {
            this.duration = timeUnit.toMillis(duration);
            return this;
        }

        @Override
        public Broadcast.Builder<PokemonSpec> duration(int durationSeconds) {
            return this.duration(durationSeconds, TimeUnit.SECONDS);
        }

        @Override
        public Broadcast.Builder<PokemonSpec> lines(String... lines) {
            this.text = Arrays.asList(lines);
            return this;
        }

        @Override
        public Broadcast.Builder<PokemonSpec> lines(List<String> lines) {
            this.text = lines;
            return this;
        }

        @Override
        public Broadcast.Builder<PokemonSpec> display(PokemonSpec display) {
            this.display = display;
            return this;
        }

        @Override
        public Broadcast build() {
            return new PixelmonBroadcast(this.duration, this.layout, this.text, this.display);
        }
    }
}
