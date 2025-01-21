package com.github.jaeukkang12.elib;

import org.bukkit.plugin.java.JavaPlugin;

public class ELibPlugin extends JavaPlugin {
    // PLUGIN INSTANCE
    private static JavaPlugin plugin;

    @Override
    public void onEnable() {
        // PLUGIN INSTANCE
        plugin = this;
    }

    @Override
    public void onDisable() {
        // SCHEDULER
//        Bukkit.getScheduler().cancelTasks(plugin);
    }

    public static JavaPlugin getPlugin() {
        return plugin;
    }
}