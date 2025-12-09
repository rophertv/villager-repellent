package com.example.villagerrepellent;


import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Villager;
import org.bukkit.entity.memory.MemoryKey;

public class VillagerPoiScrubber implements Runnable {

    private final VillagerRepellentPlugin plugin;

    public VillagerPoiScrubber(VillagerRepellentPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        for (World world : Bukkit.getWorlds()) {
            for (Villager villager : world.getEntitiesByClass(Villager.class)) {
                scrubVillager(villager);
            }
        }
    }

    private void scrubVillager(Villager villager) {
        // HOME (bed)
        Location home = villager.getMemory(MemoryKey.HOME);
        if (home != null && plugin.isBanned(home)) {
            villager.setMemory(MemoryKey.HOME, null);
        }

        // JOB_SITE (workstation)
        Location jobSite = villager.getMemory(MemoryKey.JOB_SITE);
        if (jobSite != null && plugin.isBanned(jobSite)) {
            villager.setMemory(MemoryKey.JOB_SITE, null);
        }

        // Optional extras
        Location potentialJob = villager.getMemory(MemoryKey.POTENTIAL_JOB_SITE);
        if (potentialJob != null && plugin.isBanned(potentialJob)) {
            villager.setMemory(MemoryKey.POTENTIAL_JOB_SITE, null);
        }

        Location meetingPoint = villager.getMemory(MemoryKey.MEETING_POINT);
        if (meetingPoint != null && plugin.isBanned(meetingPoint)) {
            villager.setMemory(MemoryKey.MEETING_POINT, null);
        }
    }
}
