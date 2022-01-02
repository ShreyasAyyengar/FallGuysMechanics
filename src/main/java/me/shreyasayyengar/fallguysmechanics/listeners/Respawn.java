package me.shreyasayyengar.fallguysmechanics.listeners;

import me.shreyasayyengar.fallguysmechanics.FallGuysMechanicsPlugin;
import me.shreyasayyengar.fallguysmechanics.utils.Config;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class Respawn implements Listener {

    @EventHandler
    private void onSpawn(PlayerRespawnEvent e) {
        new BukkitRunnable() {
            @Override
            public void run() {
                Player player = e.getPlayer();
                player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 1000000, Config.getAmplifier("jump-boost"), false, false, false));
            }
        }.runTaskLater(FallGuysMechanicsPlugin.getInstance(), 10);
    }
}
