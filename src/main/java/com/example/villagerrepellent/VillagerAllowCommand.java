package com.example.villagerrepellent;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VillagerAllowCommand implements CommandExecutor {

    private final VillagerRepellentPlugin plugin;

    public VillagerAllowCommand(VillagerRepellentPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Players only.");
            return true;
        }

        if (!sender.hasPermission("villagerrepellent.allow")) {
            sender.sendMessage("You don't have permission to use this command.");
            return true;
        }

        Block target = player.getTargetBlockExact(10);
        if (target == null) {
            player.sendMessage("No block in sight within 10 blocks.");
            return true;
        }

        // Normalize (handles beds: FOOT -> HEAD)
        Location loc = plugin.normalizePoiLocation(target);

        // 1) Remove from banned list
        plugin.allow(loc);

        // 2) Functionally "replace" the block to refresh POI state
        Block realBlock = loc.getBlock();
        if (realBlock.getType() != Material.AIR) {
            BlockData data = realBlock.getBlockData();
            Material type = realBlock.getType();

            // Clear it (no drops)
            realBlock.setType(Material.AIR, true);

            // Restore it with same data (orientation etc.)
            realBlock.setType(type, false);
            realBlock.setBlockData(data, true);
        }

        player.sendMessage(
                String.format(
                        "Villagers are now allowed to use block at %s (%d,%d,%d). Block state refreshed.",
                        loc.getWorld().getName(),
                        loc.getBlockX(),
                        loc.getBlockY(),
                        loc.getBlockZ()
                )
        );

        return true;
    }
}
