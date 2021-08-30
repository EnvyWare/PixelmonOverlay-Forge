package com.envyful.pixelmon.overlay.forge.command;

import com.envyful.api.command.annotate.Command;
import com.envyful.api.command.annotate.SubCommands;
import com.envyful.api.command.annotate.executor.CommandProcessor;
import com.envyful.api.command.annotate.executor.Sender;
import com.envyful.api.forge.chat.UtilChatColour;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.text.TextComponentString;

@Command(
        value = "overlay",
        description = "Root command",
        aliases = {
                "pixelmonoverlay"
        }
)
@SubCommands({
        ReloadCommand.class,
        ToggleCommand.class,
        BroadcastCommand.class
})
public class OverlayCommand {

    @CommandProcessor
    public void onCommand(@Sender ICommandSender sender, String[] args) {
        sender.sendMessage(new TextComponentString(UtilChatColour.translateColourCodes('&',
                "&e&l(!) &e/overlay <reload|toggle>")));
    }
}
