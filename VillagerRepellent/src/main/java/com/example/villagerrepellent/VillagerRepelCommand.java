package com.example.villagerrepellent;


import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VillagerRepelCommand implements CommandExecutor {

    private final VillagerRepellentPlugin plugin;

    public VillagerRepelCommand(VillagerRepellentPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("Players only.");
            return true;
        }

        if (!sender.hasPermission("villagerrepellent.repel")) {
            sender.sendMessage("You don't have permission to use this command.");
            return true;
        }

        Block target = player.getTargetBlockExact(10);
        if (target == null) {
            player.sendMessage("No block in sight within 10 blocks.");
            return true;
        }

        // Normalize (handles bed FOOT -> HEAD)
        Location loc = plugin.normalizePoiLocation(target);
        plugin.ban(loc);

        player.sendMessage(
                String.format(
                        "Villagers are now repelled from block at %s (%d,%d,%d).",
                        loc.getWorld().getName(),
                        loc.getBlockX(),
                        loc.getBlockY(),
                        loc.getBlockZ()
                )
        );

        return true;
    }
}
