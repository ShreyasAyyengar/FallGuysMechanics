package me.shreyasayyengar.fallguysmechanics.commands;

import me.shreyasayyengar.fallguysmechanics.FallGuysMechanicsPlugin;
import me.shreyasayyengar.fallguysmechanics.utils.Utility;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ConfigReloadCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender.hasPermission("fallguysmechanics.reload")) {
            FallGuysMechanicsPlugin.getInstance().reloadConfig();
            sender.sendMessage(Utility.colourise("&aConfig.yml reloaded!"));
        }

        return false;
    }
}
