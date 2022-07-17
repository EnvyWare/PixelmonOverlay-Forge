package com.envyful.pixelmon.overlay.forge.impl.broadcast;

import com.envyful.api.forge.chat.UtilChatColour;
import com.envyful.api.player.EnvyPlayer;
import com.envyful.papi.api.util.UtilPlaceholder;
import com.envyful.pixelmon.overlay.api.Broadcast;
import com.envyful.pixelmon.overlay.forge.task.ClearTask;
import com.google.common.collect.Lists;
import com.pixelmonmod.pixelmon.api.overlay.notice.EnumOverlayLayout;
import com.pixelmonmod.pixelmon.api.overlay.notice.NoticeOverlay;
import net.minecraft.entity.player.ServerPlayerEntity;
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
        List<String> lines = Lists.newArrayList();
        NoticeOverlay.Builder builder = NoticeOverlay.builder()
                .setLayout(this.layout)
                .setItemStack(this.display);

        for (String s : this.text) {
            lines.add(UtilChatColour.translateColourCodes('&',
                    UtilPlaceholder.replaceIdentifiers((ServerPlayerEntity) player.getParent(), s)));
        }

        builder.setLines(lines);
        ClearTask.updateClearTime(player, System.currentTimeMillis() + this.duration);
        builder.sendTo((ServerPlayerEntity) player.getParent());
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
