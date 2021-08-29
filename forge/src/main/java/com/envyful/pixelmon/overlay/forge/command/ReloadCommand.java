package com.envyful.pixelmon.overlay.forge.command;

import com.envyful.api.command.annotate.Child;
import com.envyful.api.command.annotate.Command;
import com.envyful.api.command.annotate.Permissible;
import com.envyful.api.command.annotate.executor.CommandProcessor;
import com.envyful.api.command.annotate.executor.Sender;
import com.envyful.api.forge.chat.UtilChatColour;
import com.envyful.pixelmon.overlay.forge.PixelmonOverlayForge;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.text.TextComponentString;

@Command(
        value = "reload",
        description = "Reload command"
)
@Permissible("pixelmon.overlays.command.reload")
@Child
public class ReloadCommand {

    @CommandProcessor
    public void onCommand(@Sender ICommandSender sender, String[] args) {
        PixelmonOverlayForge.getInstance().loadConfig();
        sender.sendMessage(new TextComponentString(UtilChatColour.translateColourCodes('&',
                "&e&l(!) &eReloaded config")));

    }
}
