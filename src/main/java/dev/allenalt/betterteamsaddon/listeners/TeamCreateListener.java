package dev.allenalt.betterteamsaddon.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;

import dev.allenalt.betterteamsaddon.BetterTeamsAddon;
import dev.allenalt.betterteamsaddon.gui.CreateTeamMenu;

public class TeamCreateListener implements Listener {

    private final BetterTeamsAddon plugin;

    public TeamCreateListener(BetterTeamsAddon plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onTeamCreateCommand(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage().toLowerCase();

        // Detect "/team create <name>" or "/t create <name>"
        if (message.startsWith("/team create") || message.startsWith("/t create")) {
            // Run the GUI a few ticks later (after BetterTeams processes the command)
            Bukkit.getScheduler().runTaskLater(plugin, () -> {
                if (player.isOnline()) {
                    CreateTeamMenu.open(player);
                }
            }, 20L); // delay ~1 second to ensure team exists
        }
    }
}
