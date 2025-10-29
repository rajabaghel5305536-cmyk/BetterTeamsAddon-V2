package dev.allenalt.betterteamsaddon.gui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import dev.allenalt.betterteamsaddon.BetterTeamsAddon;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CreateTeamMenu implements Listener {

    private final BetterTeamsAddon plugin;

    public CreateTeamMenu(BetterTeamsAddon plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    public static void open(Player player) {
        BetterTeamsAddon plugin = BetterTeamsAddon.getInstance();
        ConfigurationSection menu = plugin.getConfig().getConfigurationSection("menu");

        String title = ChatColor.translateAlternateColorCodes('&', menu.getString("title", "&6Create Team"));
        int size = menu.getInt("size", 27);
        Inventory inv = Bukkit.createInventory(null, size, title);

        ConfigurationSection items = menu.getConfigurationSection("items");
        if (items != null) {
            for (String key : items.getKeys(false)) {
                int slot = Integer.parseInt(key);
                ConfigurationSection itemSec = items.getConfigurationSection(key);
                if (itemSec == null) continue;

                Material mat = Material.matchMaterial(itemSec.getString("material", "STONE"));
                if (mat == null) mat = Material.STONE;

                ItemStack item = new ItemStack(mat);
                ItemMeta meta = item.getItemMeta();
                if (meta != null) {
                    meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', itemSec.getString("name", "&7Item")));
                    List<String> lore = new ArrayList<>();
                    for (String line : itemSec.getStringList("lore")) {
                        lore.add(ChatColor.translateAlternateColorCodes('&', line));
                    }
                    meta.setLore(lore);
                    item.setItemMeta(meta);
                }
                inv.setItem(slot, item);
            }
        }

        player.openInventory(inv);
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (e.getView().getTitle().contains("Create")) {
            e.setCancelled(true); // Prevent taking items
            Player player = (Player) e.getWhoClicked();
            int slot = e.getRawSlot();

            // Example: close menu if player clicks "Cancel"
            String itemName = e.getCurrentItem() != null && e.getCurrentItem().hasItemMeta()
                    ? ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName())
                    : "";

            if (itemName.equalsIgnoreCase("Cancel")) {
                player.closeInventory();
                player.sendMessage(ChatColor.RED + "Menu closed.");
            } else {
                player.sendMessage(ChatColor.GREEN + "You clicked: " + itemName);
            }
        }
    }
}
