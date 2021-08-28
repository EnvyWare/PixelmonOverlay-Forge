package com.envyful.pixelmon.overlay.forge.task;

import com.envyful.api.forge.concurrency.UtilForgeConcurrency;
import com.envyful.api.forge.player.util.UtilPlayer;
import com.envyful.api.player.EnvyPlayer;
import com.google.common.collect.Maps;
import com.pixelmonmod.pixelmon.Pixelmon;
import com.pixelmonmod.pixelmon.comm.packetHandlers.custom.overlays.CustomNoticePacket;
import net.minecraft.entity.player.EntityPlayerMP;

import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

public class ClearTask implements Runnable {

    private static final CustomNoticePacket CLEAR_PACKET = new CustomNoticePacket().setEnabled(false);

    private static ClearTask instance;

    private final Map<UUID, Long> clearTimes = Maps.newConcurrentMap();

    public ClearTask() {
        instance = this;
    }

    public static void updateClearTime(EnvyPlayer<?> player, long time) {
        instance.clearTimes.put(player.getUuid(), time);
    }

    @Override
    public void run() {
        Iterator<Map.Entry<UUID, Long>> iterator = clearTimes.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<UUID, Long> next = iterator.next();
            EntityPlayerMP player = UtilPlayer.getOnlinePlayer(next.getKey());

            if (player == null) {
                iterator.remove();
                continue;
            }

            if (System.currentTimeMillis() > next.getValue()) {
                continue;
            }

            iterator.remove();
            UtilForgeConcurrency.runSync(() -> Pixelmon.network.sendTo(CLEAR_PACKET, player));
        }
    }
}
