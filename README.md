# VillagerRepellent
A lightweight Paper plugin that lets you repel villagers from using specific beds and workstations — works by logging blocks to a list of banned coordinates for villager use. Perfect for keeping villagers out of personal homes, or using workstation blocks as decoration. (No more fishermen in your barrel room!)


## Features
- Repel villagers from any blocks at specified coordinates  
- Works for beds, workstations, bells  
- Commands to both blacklist and whitelist blocks
- 100% vanilla-friendly
- Supports Paper 1.21.x
- Extremely lightweight


## How It Works

### ❌ Repellent Mode (/villagerrepel, /vr)
When you repel a block:
- The block’s position is added to a banned list  
- Every 10 seconds, the plugin checks all villagers' memories for that block position  
  - If their HOME or JOB_SITE matches a banned block, their memory is nulled  
- Villagers immediately ignore that block and seek a new one

### ✔️ Allow Mode (/villagerallow, /va)
When you allow a block:
- It is removed from the banned list  
- The plugin replaces the block with itself, triggering a vanilla POI refresh  
  (same effect as breaking and placing the block)  
- Villagers reconsider it during the next work cycle


##  Installation
1. Download the latest release JAR  
2. Place it in your Paper server’s `plugins/` folder  
3. Restart the server  

Supports Paper 1.21.x.  

##  Permissions
- villagerrepellent.repel:
    - description: Allows repelling villagers from POI blocks.
    - default: op
- villagerrepellent.allow:
    - description: Allows allowing villagers to use POI blocks again.
    - default: op

##  Compatibilities/Conflicts
I'm relatively new to this, so feel free to let me know if this plugin conflicts with any popular plugins and I'll look to correct it!


##  Config
`config.yml`:
```yaml
banned-positions: []
