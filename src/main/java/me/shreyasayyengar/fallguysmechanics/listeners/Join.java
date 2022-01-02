package me.shreyasayyengar.fallguysmechanics.listeners;

import me.shreyasayyengar.fallguysmechanics.utils.Config;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Join implements Listener {

    @EventHandler
    private void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 1000000, Config.getAmplifier("jump-boost"), false, false, false));
    }
}
