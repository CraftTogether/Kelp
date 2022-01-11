package com.github.crafttogether.kelp;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import javax.security.auth.login.LoginException;
import java.nio.file.Files;
import java.nio.file.Path;

public final class Plugin extends JavaPlugin {

    public static JavaPlugin plugin;

    @Override
    public void onEnable() {
        plugin = this;

        getConfig().options().copyDefaults();
        saveDefaultConfig();
        try {
            getConfig().load(Files.newBufferedReader(Path.of(getDataFolder() + "/config.yml")));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Kelp.getInstance().connect();
        } catch (LoginException | InterruptedException e) {
            Bukkit.getLogger().warning("Failed to connect to discord");
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        Kelp.getInstance().disconnect();
    }

    public static JavaPlugin getPlugin() {
        return plugin;
    }
}
