package dev.allenalt.betterteamsaddon;

import org.bukkit.plugin.java.JavaPlugin;
import dev.allenalt.betterteamsaddon.listeners.TeamCreateListener;

public final class BetterTeamsAddon extends JavaPlugin {

    @Override
    public void onEnable() {
        getLogger().info("BetterTeamsAddon v1.0.0 enabled!");
        getServer().getPluginManager().registerEvents(new TeamCreateListener(this), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("BetterTeamsAddon disabled.");
    }
}
