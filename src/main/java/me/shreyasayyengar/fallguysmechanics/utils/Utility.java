package me.shreyasayyengar.fallguysmechanics.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.BoundingBox;

import java.util.ArrayList;
import java.util.List;

public class Utility {

    public static String colourise(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static void bsout(String message) {
        Bukkit.getLogger().info(message);
    }

    public static void removePotionEffect(Player player, PotionEffectType type) {
        if (player.hasPotionEffect(type)) {
            player.removePotionEffect(type);
        }
    }

    public static boolean hasPotionEffect(Player player, PotionEffectType type) {
        return player.hasPotionEffect(type);
    }

    public static boolean isTouching(Player player, Block block) {
        Location playerBottomLeft = player.getLocation().clone().subtract(0.3, 0, 0.3);
        Location playerTopRight = player.getLocation().clone().add(0.3, 1.8, 0.3);
        BoundingBox playerBox = BoundingBox.of(playerBottomLeft, playerTopRight);
        BoundingBox blockBox = BoundingBox.of(block);

        blockBox.expand(0.1);

        try {
            playerBox.intersection(blockBox);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }

    public static List<Block> getRegionBlocks(Player player) {

        Location loc1 = player.getLocation().subtract(2, 2, 2);
        Location loc2 = player.getLocation().add(2, 2, 2);

        List<Block> blocks = new ArrayList<>();

        for (double x = loc1.getX(); x <= loc2.getX(); x++) {
            for (double y = loc1.getY(); y <= loc2.getY(); y++) {
                for (double z = loc1.getZ(); z <= loc2.getZ(); z++) {
                    Location loc = new Location(player.getWorld(), x, y, z);
                    blocks.add(loc.getBlock());
                }
            }
        }

        return blocks;
    }
}
