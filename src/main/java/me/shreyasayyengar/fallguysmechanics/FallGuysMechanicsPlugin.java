package me.shreyasayyengar.fallguysmechanics;

import me.shreyasayyengar.fallguysmechanics.commands.ConfigReloadCommand;
import me.shreyasayyengar.fallguysmechanics.listeners.Join;
import me.shreyasayyengar.fallguysmechanics.listeners.Move;
import me.shreyasayyengar.fallguysmechanics.listeners.Respawn;
import me.shreyasayyengar.fallguysmechanics.utils.Config;
import me.shreyasayyengar.fallguysmechanics.utils.Utility;
import org.bukkit.plugin.java.JavaPlugin;

public final class FallGuysMechanicsPlugin extends JavaPlugin {

    private static FallGuysMechanicsPlugin INSTANCE;

    public static FallGuysMechanicsPlugin getInstance() {
        return INSTANCE;
    }

    @Override
    public void onEnable() {
        INSTANCE = this;
        getLogger().info(Utility.colourise("&bPlugin started with no errors present"));

        Config.init(this);

        getServer().getPluginManager().registerEvents(new Move(), this);
        getServer().getPluginManager().registerEvents(new Join(), this);
        getServer().getPluginManager().registerEvents(new Respawn(), this);

        getCommand("fallguysreload").setExecutor(new ConfigReloadCommand());
    }

    @Override
    public void onDisable() {
        getLogger().info(Utility.colourise("&cPlugin shutting down..."));
    }
}
