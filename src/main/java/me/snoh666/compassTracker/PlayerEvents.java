package me.snoh666.compassTracker;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CompassMeta;

public class PlayerEvents implements Listener {

     final PluginMessages message = new PluginMessages();

     @EventHandler
     public void onPlayerEnterPortal(PlayerPortalEvent event) {
         Player player = event.getPlayer();
         if(event.getTo().getWorld().getEnvironment() == World.Environment.NETHER) return;
         for(ItemStack i : player.getInventory().getContents()) {
             if(i != null && i.getType().equals(Material.COMPASS)) {
                 CompassMeta compassMeta = (CompassMeta) i.getItemMeta();
                 compassMeta.setLodestoneTracked(true);
                 i.setItemMeta(compassMeta);
             }
         }
     }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        Material item = Material.COMPASS;
        ItemStack itemStack = new ItemStack(item, 1);
        Player player = event.getPlayer();
        player.getInventory().addItem(itemStack);
    }

}
