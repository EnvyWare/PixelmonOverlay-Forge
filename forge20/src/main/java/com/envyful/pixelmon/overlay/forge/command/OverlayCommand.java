package com.envyful.pixelmon.overlay.forge.command;

import com.envyful.api.command.annotate.Command;
import com.envyful.api.command.annotate.SubCommands;
import com.envyful.api.command.annotate.executor.CommandProcessor;
import com.envyful.api.command.annotate.executor.Sender;
import com.envyful.api.forge.chat.UtilChatColour;
import net.minecraft.commands.CommandSource;

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
    public void onCommand(@Sender CommandSource sender, String[] args) {
        sender.sendSystemMessage(UtilChatColour.colour("&e&l(!) &e/overlay <reload|toggle>"));
    }
}
