package com.envyful.pixelmon.overlay.forge.impl.broadcast;

import com.envyful.api.player.EnvyPlayer;
import com.envyful.pixelmon.overlay.api.Broadcast;
import com.envyful.pixelmon.overlay.forge.impl.broadcast.pixelmon.PixelmonDisplay;
import com.envyful.pixelmon.overlay.forge.task.ClearTask;
import com.pixelmonmod.pixelmon.api.overlay.notice.EnumOverlayLayout;
import com.pixelmonmod.pixelmon.api.overlay.notice.NoticeOverlay;
import com.pixelmonmod.pixelmon.api.pokemon.PokemonSpec;
import net.minecraft.entity.player.EntityPlayerMP;

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
        NoticeOverlay.Builder builder = NoticeOverlay.builder().setLines(this.text)
                .setLayout(this.layout);

        if (this.display.isSprite()) {
            builder.setPokemonSprite(new PokemonSpec(this.display.getSpec()));
        } else {
            builder.setPokemon3D(new PokemonSpec(this.display.getSpec()));
        }

        builder.sendTo((EntityPlayerMP) player.getParent());
        ClearTask.updateClearTime(player, System.currentTimeMillis() + this.duration);
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
