package com.eripe14.flamecode.command.handler;

import com.eripe14.flamecode.config.implementation.MessageConfig;
import com.eripe14.flamecode.message.MessageAnnouncer;
import dev.rollczi.litecommands.command.LiteInvocation;
import dev.rollczi.litecommands.handle.InvalidUsageHandler;
import dev.rollczi.litecommands.schematic.Schematic;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import panda.utilities.text.Formatter;

import java.util.List;

public class InvalidUsage implements InvalidUsageHandler<CommandSender> {

    private final MessageAnnouncer messageAnnouncer;
    private final MessageConfig messageConfig;

    public InvalidUsage(MessageAnnouncer messageAnnouncer, MessageConfig messageConfig) {
        this.messageAnnouncer = messageAnnouncer;
        this.messageConfig = messageConfig;
    }

    @Override
    public void handle(CommandSender sender, LiteInvocation invocation, Schematic schematic) {
        List<String> schematics = schematic.getSchematics();

        Player player = (Player) sender;

        Formatter formatter = new Formatter();

        formatter.register("{COMMAND}", schematics.get(0));

        if (schematics.size() == 1) {
            this.messageAnnouncer.sendMessage(player.getUniqueId(), formatter.format(this.messageConfig.wrongUsage.invalidUsage));
            return;
        }

        this.messageAnnouncer.sendMessage(player.getUniqueId(), this.messageConfig.wrongUsage.invalidUsageHeader);

        for (String schema : schematics) {
            this.messageAnnouncer.sendMessage(player.getUniqueId(), this.messageConfig.wrongUsage.invalidUsageEntry + schema);
        }
    }

}
