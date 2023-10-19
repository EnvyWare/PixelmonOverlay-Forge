package com.envyful.pixelmon.overlay.forge.command;

import com.envyful.api.command.annotate.Command;
import com.envyful.api.command.annotate.executor.CommandProcessor;
import com.envyful.api.command.annotate.executor.Sender;
import com.envyful.api.command.annotate.permission.Permissible;
import com.envyful.api.forge.chat.UtilChatColour;
import com.envyful.pixelmon.overlay.forge.PixelmonOverlayForge;
import net.minecraft.commands.CommandSource;

@Command(
        value = "reload"
)
@Permissible("pixelmon.overlays.command.reload")
public class ReloadCommand {

    @CommandProcessor
    public void onCommand(@Sender CommandSource sender, String[] args) {
        PixelmonOverlayForge.getInstance().loadConfig();
        sender.sendSystemMessage(UtilChatColour.colour("&e&l(!) &eReloaded config"));
    }
}
