package com.github.jaeukkang12.sample;

import org.bukkit.plugin.java.JavaPlugin;

public class SamplePlugin extends JavaPlugin {
    // PLUGIN INSTANCE
    private static JavaPlugin plugin;

    @Override
    public void onEnable() {
        // PLUGIN INSTANCE
        plugin = this;

        getLogger().info("Hello World!");
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