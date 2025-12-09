VillagerRepellent

A lightweight Paper plugin that lets you repel villagers from using specific beds, workstations, and other POI locations — per block, not per type.

Perfect for villager trading halls, iron farms, breeder control, or any setup where you need precise POI management without changing vanilla mechanics.

⭐ Features

Repel villagers from ANY specific block position

Works for:

Beds (with automatic head/foot detection)

Workstations (lectern, loom, smithing table, etc.)

Meeting points / bells

VillagerAllow command restores normal behavior

POI refresh ensures villagers reconsider the block immediately

100% vanilla-friendly — no NMS, no AI rewrites

Supports Paper 1.21+

Extremely lightweight and low-lag

🔧 How It Works

Villagers in Minecraft store POI locations (beds, job sites, meeting points) in their internal memory.

VillagerRepellent does two things:

1️⃣ Repellent Mode (/villagerrepel)

When you repel a block:

The plugin adds its position to a banned list

Every 10 seconds, the plugin checks every villager:

If their HOME or JOB_SITE matches a banned block → it is erased

Villagers immediately stop using that block and seek a new one

2️⃣ Allow Mode (/villagerallow)

When you allow a block:

It is removed from the banned list

The plugin replaces the block with itself, triggering Minecraft's built-in POI refresh
(equivalent to breaking and replacing it manually)

Villagers will consider it again on the next work cycle

🛏 Bed support

VillagerRepellent automatically converts:

Bed foot → correct head position
so you don't accidentally repel the wrong half.

📦 Installation

Download the latest release JAR

Drop it into your server’s plugins/ folder

Start/restart the server

You’re done!

Supports Paper 1.21+.
Spigot may work, but Paper’s memory API is recommended.

🕹 Commands
/villagerrepel

Alias: /vr
Repels villagers from the block you're looking at.

Villagers will:

Forget it as a bed or job site

Stop pathfinding to it

Find a new POI instead

/villagerallow

Alias: /va
Allows villagers to use the block again.

Also performs a POI refresh, just like breaking and replacing the block.

🎯 Use Cases

Trading halls
Stop villagers from binding to the wrong workstation.

Iron farms
Prevent villagers from choosing the wrong bed.

Breeder control
Disable a specific bed during maintenance.

Villager transport systems
Ensure no villager claims a bed mid-route.

Custom adventure maps
Hard-code which POIs villagers can or cannot use.

⚙ Configuration

config.yml:

banned-positions: []


Entries are automatically managed by commands:

Upon /villagerrepel, a block’s world,x,y,z is added

Upon /villagerallow, its entry is removed

No manual editing required.

🧠 Technical Details

Runs a scrub cycle every 10 seconds

Uses official Bukkit Memory API:

MemoryKey.HOME

MemoryKey.JOB_SITE

MemoryKey.POTENTIAL_JOB_SITE

MemoryKey.MEETING_POINT

Zero NMS = maximum compatibility across Paper updates

POI refresh is done via:

block.setType(AIR);
block.setType(originalType);
block.setBlockData(originalData);


to trigger vanilla POI rebuild logic.

📜 License

MIT License – do whatever you want, just credit the project.

❤️ Support / Feedback

If you encounter an issue or want a feature:

Open a GitHub issue

Message on Modrinth

Or ask for help in the PaperMC community

Happy villager-controlling! 🧑‍🌾👉🚫🛏️
