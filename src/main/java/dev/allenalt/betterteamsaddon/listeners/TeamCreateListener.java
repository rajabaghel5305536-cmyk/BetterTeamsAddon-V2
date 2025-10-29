package dev.allenalt.betterteamsaddon.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;

import com.booksaw.betterTeams.events.TeamCreateEvent;

import dev.allenalt.betterteamsaddon.gui.CreateTeamMenu;
import dev.allenalt.betterteamsaddon.BetterTeamsAddon;

public class TeamCreateListener implements Listener {

    private final BetterTeamsAddon plugin;

    public TeamCreateListener(BetterTeamsAddon plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onTeamCreate(TeamCreateEvent event) {
        Player player = event.getPlayer();
        if (player == null) return;

        // Delay to ensure team creation completes
        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            CreateTeamMenu.open(player);
        }, 1L);
    }
}
