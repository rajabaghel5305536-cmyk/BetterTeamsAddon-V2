package dev.allenalt.betterteamsaddon.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.ChatColor;

public class CreateTeamMenu implements Listener {

    public static void open(Player player) {
        Inventory inv = Bukkit.createInventory(null, 27, ChatColor.GOLD + "Customize Your Team");

        inv.setItem(11, createItem(Material.WHITE_BANNER, "&aSet Team Banner"));
        inv.setItem(13, createItem(Material.NAME_TAG, "&bEdit Team Name"));
        inv.setItem(15, createItem(Material.PAINTING, "&eSet Team Icon"));

        player.openInventory(inv);
    }

    private static ItemStack createItem(Material mat, String name) {
        ItemStack item = new ItemStack(mat);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        item.setItemMeta(meta);
        return item;
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (e.getView().getTitle().equals(ChatColor.GOLD + "Customize Your Team")) {
            e.setCancelled(true);
            Player player = (Player) e.getWhoClicked();

            switch (e.getSlot()) {
                case 11:
                    player.sendMessage(ChatColor.GREEN + "Team Banner customization coming soon!");
                    break;
                case 13:
                    player.sendMessage(ChatColor.AQUA + "Team Name editing feature coming soon!");
                    break;
                case 15:
                    player.sendMessage(ChatColor.YELLOW + "Team Icon customization coming soon!");
                    break;
                default:
                    break;
            }
        }
    }
}
