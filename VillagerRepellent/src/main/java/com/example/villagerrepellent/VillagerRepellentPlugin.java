package com.example.villagerrepellent;


import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.Bed;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class VillagerRepellentPlugin extends JavaPlugin {

    private final Set<BlockKey> bannedPositions = new HashSet<>();

    @Override
    public void onEnable() {
        saveDefaultConfig();
        loadBannedPositions();

        // Register commands
        getCommand("vr").setExecutor(new VillagerRepelCommand(this));
        getCommand("va").setExecutor(new VillagerAllowCommand(this));

        // Still using a periodic scrub (see explanation below)
        getServer().getScheduler().runTaskTimer(
                this,
                new VillagerPoiScrubber(this),
                20L,   // delay 1 second
                200L   // repeat every 10 seconds
        );

        getLogger().info("VillagerRepellent enabled. Loaded " + bannedPositions.size() + " banned positions.");
    }

    @Override
    public void onDisable() {
        saveBannedPositions();
    }

    // --- Public API used by commands/scrubber ---

    public boolean isBanned(Location loc) {
        return bannedPositions.contains(new BlockKey(loc));
    }

    public boolean isBanned(String world, int x, int y, int z) {
        return bannedPositions.contains(new BlockKey(world, x, y, z));
    }

    public void ban(Location loc) {
        bannedPositions.add(new BlockKey(loc));
        saveBannedPositions();
    }

    public void allow(Location loc) {
        bannedPositions.remove(new BlockKey(loc));
        saveBannedPositions();
    }

    // Normalize a clicked block to the "real" stored position if it's a bed
    public Location normalizePoiLocation(Block block) {
        Location loc = block.getLocation();

        if (block.getBlockData() instanceof Bed bed) {
            // If we clicked the FOOT, move one block in the bed's facing direction to get the HEAD.
            if (bed.getPart() == Bed.Part.FOOT) {
                loc = loc.add(
                        bed.getFacing().getModX(),
                        0,
                        bed.getFacing().getModZ()
                );
            }
        }

        return loc;
    }


    private void loadBannedPositions() {
        bannedPositions.clear();
        List<String> list = getConfig().getStringList("banned-positions");
        for (String s : list) {
            try {
                bannedPositions.add(BlockKey.fromString(s));
            } catch (IllegalArgumentException e) {
                getLogger().warning("Skipping invalid banned position entry: " + s);
            }
        }
    }

    private void saveBannedPositions() {
        List<String> list = bannedPositions.stream()
                .map(BlockKey::toString)
                .collect(Collectors.toList());
        getConfig().set("banned-positions", list);
        saveConfig();
    }

    public boolean worldExists(String worldName) {
        World world = getServer().getWorld(worldName);
        return world != null;
    }
}
