package dev.allenalt.betterteamsaddon;

import org.bukkit.plugin.java.JavaPlugin;

import dev.allenalt.betterteamsaddon.listeners.TeamCreateListener;
import dev.allenalt.betterteamsaddon.gui.CreateTeamMenu;

public class BetterTeamsAddon extends JavaPlugin {

    private static BetterTeamsAddon instance;

    @Override
    public void onEnable() {
        instance = this;

        // Save config.yml if it doesn't exist
        saveDefaultConfig();

        // Register listeners
        getServer().getPluginManager().registerEvents(new TeamCreateListener(this), this);
        new CreateTeamMenu(this); // Registers its own click listener

        getLogger().info("=====================================");
        getLogger().info(" BetterTeamsAddon v1.0.0 Enabled ✅");
        getLogger().info(" Author: Dev_Allenalt_tw");
        getLogger().info("=====================================");
    }

    @Override
    public void onDisable() {
        getLogger().info("BetterTeamsAddon disabled.");
    }

    public static BetterTeamsAddon getInstance() {
        return instance;
    }
}
