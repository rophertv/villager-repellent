package com.example.villagerrepellent;


import org.bukkit.Location;

import java.util.Objects;

public final class BlockKey {
    private final String world;
    private final int x;
    private final int y;
    private final int z;

    public BlockKey(Location loc) {
        this.world = loc.getWorld().getName();
        this.x = loc.getBlockX();
        this.y = loc.getBlockY();
        this.z = loc.getBlockZ();
    }

    public BlockKey(String world, int x, int y, int z) {
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public String world() {
        return world;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    public int z() {
        return z;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BlockKey)) return false;
        BlockKey blockKey = (BlockKey) o;
        return x == blockKey.x &&
                y == blockKey.y &&
                z == blockKey.z &&
                world.equals(blockKey.world);
    }

    @Override
    public int hashCode() {
        return Objects.hash(world, x, y, z);
    }

    @Override
    public String toString() {
        return world + "," + x + "," + y + "," + z;
    }

    public static BlockKey fromString(String s) {
        String[] parts = s.split(",");
        if (parts.length != 4) {
            throw new IllegalArgumentException("Invalid BlockKey string: " + s);
        }
        String world = parts[0];
        int x = Integer.parseInt(parts[1]);
        int y = Integer.parseInt(parts[2]);
        int z = Integer.parseInt(parts[3]);
        return new BlockKey(world, x, y, z);
    }
}
