package com.envyful.pixelmon.overlay.forge.command;

import com.envyful.api.command.annotate.Command;
import com.envyful.api.command.annotate.executor.CommandProcessor;
import com.envyful.api.command.annotate.executor.Sender;
import com.envyful.api.command.annotate.permission.Permissible;
import com.envyful.api.forge.chat.UtilChatColour;
import com.envyful.api.forge.player.ForgeEnvyPlayer;
import com.envyful.pixelmon.overlay.forge.PixelmonOverlayForge;
import com.envyful.pixelmon.overlay.forge.impl.OverlayAttribute;
import net.minecraft.server.level.ServerPlayer;

@Command(
        value = "toggle"
)
@Permissible("pixelmon.overlays.command.toggle")
public class ToggleCommand {

    @CommandProcessor
    public void onCommand(@Sender ServerPlayer sender, String[] args) {
        ForgeEnvyPlayer player = PixelmonOverlayForge.getInstance().getPlayerManager().getPlayer(sender);
        OverlayAttribute attribute = player.getAttribute(OverlayAttribute.class);

        if (attribute == null) {
            return;
        }

        attribute.setToggled(!attribute.isToggled());
        sender.sendSystemMessage(UtilChatColour.colour("&e&l(!) &" + (attribute.isToggled() ? "cDisabled&e" : "aEnabled&e") + " pixelmon overlay!"));
    }
}
