package com.envyful.pixelmon.overlay.forge.impl.broadcast;

import com.envyful.api.player.EnvyPlayer;
import com.envyful.pixelmon.overlay.api.Broadcast;
import com.pixelmonmod.pixelmon.api.overlay.notice.EnumOverlayLayout;
import com.pixelmonmod.pixelmon.api.overlay.notice.NoticeOverlay;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ItemBroadcast extends TextBroadcast {

    private final ItemStack display;

    public ItemBroadcast(long duration, EnumOverlayLayout layout, List<String> text, ItemStack display) {
        super(duration, layout, text);

        this.display = display;
    }

    @Override
    public void send(EnvyPlayer<?> player) {
        NoticeOverlay.builder().addLines(this.text)
                .setLayout(this.layout)
                .setItemStack(this.display)
                .sendTo((EntityPlayerMP) player.getParent());

        //TODO: add clearing task
    }

    public static class Builder implements Broadcast.Builder<ItemStack> {

        private EnumOverlayLayout layout;
        private long duration;
        private ItemStack display;
        private List<String> text;

        @Override
        public Broadcast.Builder<ItemStack> layout(EnumOverlayLayout layout) {
            this.layout = layout;
            return this;
        }

        @Override
        public Broadcast.Builder<ItemStack> duration(long duration, TimeUnit timeUnit) {
            this.duration = timeUnit.toMillis(duration);
            return this;
        }

        @Override
        public Broadcast.Builder<ItemStack> duration(int durationSeconds) {
            return this.duration(durationSeconds, TimeUnit.SECONDS);
        }

        @Override
        public Broadcast.Builder<ItemStack> lines(String... lines) {
            this.text = Arrays.asList(lines);
            return this;
        }

        @Override
        public Broadcast.Builder<ItemStack> lines(List<String> lines) {
            this.text = lines;
            return this;
        }

        @Override
        public Broadcast.Builder<ItemStack> display(ItemStack display) {
            this.display = display;
            return this;
        }

        @Override
        public Broadcast build() {
            return new ItemBroadcast(this.duration, this.layout, this.text, this.display);
        }
    }
}
