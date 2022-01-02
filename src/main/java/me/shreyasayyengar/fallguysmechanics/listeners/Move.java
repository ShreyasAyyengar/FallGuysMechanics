package me.shreyasayyengar.fallguysmechanics.listeners;

import me.shreyasayyengar.fallguysmechanics.FallGuysMechanicsPlugin;
import me.shreyasayyengar.fallguysmechanics.utils.Config;
import me.shreyasayyengar.fallguysmechanics.utils.Utility;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Directional;
import org.bukkit.block.data.type.Piston;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class Move implements Listener {

    @EventHandler
    private void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        if (player.getGameMode() == GameMode.SURVIVAL || player.getGameMode() == GameMode.ADVENTURE) {

            if (Config.getWhitelistedWorlds().contains(player.getWorld().getName().toLowerCase())) {

                Location location = player.getLocation().clone().subtract(0, 1, 0);

                if (location.getBlock().getType().equals(Material.GOLD_BLOCK)) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1, Config.getAmplifier("speed"), false, false, false));
                }

                if (location.getBlock().getType().equals(Material.MAGENTA_GLAZED_TERRACOTTA)) {

                    if (location.getBlock().getBlockData() instanceof Directional bmeta) {

                        if (bmeta.getFacing() == BlockFace.NORTH && (player.getLocation().getYaw() >= -20 && player.getLocation().getYaw() <= 20)) {
                            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 8, Config.getAmplifier("terracotta.speed"), false, false, false));

                        } else if (bmeta.getFacing() == BlockFace.EAST && (player.getLocation().getYaw() > 70 && player.getLocation().getYaw() < 110)) {
                            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 8, Config.getAmplifier("terracotta.speed"), false, false, false));

                        } else if (bmeta.getFacing() == BlockFace.SOUTH && (player.getLocation().getYaw() > 160 && player.getLocation().getYaw() < -160)) {
                            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 8, Config.getAmplifier("terracotta.speed"), false, false, false));

                        } else if (bmeta.getFacing() == BlockFace.WEST && (player.getLocation().getYaw() > -110 && player.getLocation().getYaw() < -70)) {
                            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 8, Config.getAmplifier("terracotta.speed"), false, false, false));
                        } else {
                            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 8, Config.getAmplifier("terracotta.slow"), false, false, false));
                        }
                    }
                }

                if (location.getBlock().getType().equals(Material.WARPED_PLANKS)) {

                    new BukkitRunnable() {
                        @Override
                        public void run() {

                            location.getBlock().setType(Material.CRIMSON_PLANKS);

                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    location.getBlock().setType(Material.AIR);

                                    new BukkitRunnable() {
                                        @Override
                                        public void run() {
                                            location.getBlock().setType(Material.WARPED_PLANKS);
                                        }
                                    }.runTaskLater(FallGuysMechanicsPlugin.getInstance(), Config.getAmplifier("warped-planks.back-warped"));
                                }
                            }.runTaskLater(FallGuysMechanicsPlugin.getInstance(), Config.getAmplifier("warped-planks.air"));
                        }
                    }.runTaskLater(FallGuysMechanicsPlugin.getInstance(), Config.getAmplifier("warped-planks.crimson-planks"));
                }

                Utility.getRegionBlocks(player).forEach(block -> {
                    if (block.getType().equals(Material.SLIME_BLOCK)) {
                        if (Utility.isTouching(player, block)) {

                            Location blockLocation = block.getLocation().add(.5, .5, .5);
                            Location eyeLocation = player.getEyeLocation();

                            Vector vector = eyeLocation.subtract(blockLocation).toVector().multiply(Config.getVelo("slime"));

                            player.setVelocity(vector);
                        }
                    }

                    if (block.getType().equals(Material.PISTON) || block.getType().equals(Material.STICKY_PISTON)) {
                        if (Utility.isTouching(player, block)) {

                            if (player.getFacing() == ((Directional) block.getBlockData()).getFacing()) {
                                Piston piston = (Piston) block.getBlockData();
                                piston.setExtended(true);
                                block.setBlockData(piston);

                                Location blockLocation = block.getLocation().add(.5, .5, .5);
                                Location eyeLocation = player.getEyeLocation();

                                Vector vector = eyeLocation.subtract(blockLocation).toVector().multiply(Config.getVelo("piston"));

                                player.setVelocity(vector);

                                new BukkitRunnable() {

                                    @Override
                                    public void run() {
                                        piston.setExtended(false);
                                        block.setBlockData(piston);

                                    }
                                }.runTaskLater(FallGuysMechanicsPlugin.getInstance(), 20);

                            }
                        }
                    }

                    if (block.getType().equals(Material.SPONGE)) {
                        if (Utility.isTouching(player, block)) {

                            if (!player.isSwimming()) {
                                player.setSwimming(true);
                            }
                        }
                    }

                    if (block.getType().equals(Material.PURPUR_BLOCK)) {
                        if (Utility.isTouching(player, block)) {
                            player.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, Config.getAmplifier("purpur.time"), Config.getAmplifier("purpur.amplifier"), false, false, false));
                        }
                    }
                });
            }
        }
    }
}