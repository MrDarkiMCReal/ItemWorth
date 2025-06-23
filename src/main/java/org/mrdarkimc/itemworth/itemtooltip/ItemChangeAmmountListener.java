package org.mrdarkimc.itemworth.itemtooltip;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.mrdarkimc.itemworth.Handler;

import java.util.ArrayList;
import java.util.List;

public class ItemChangeAmmountListener implements Listener {
    private Handler handler;

    {
        handler = new Handler();
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        ItemStack current = e.getCurrentItem();
        ItemStack cursor = e.getCursor();
        int slot = e.getRawSlot();
        ClickType click = e.getClick();
        System.out.println("raw slot: " + slot);
        if ((current == null || current.getType().equals(Material.AIR)) && (cursor == null || cursor.getType().equals(Material.AIR))) {
            System.out.println("Clicking air slot");
            return;
        }
        if ((cursor == null || cursor.getType().equals(Material.AIR)) && (current != null && !(current.getType().equals(Material.AIR)))) {
            //take item from inv into cursor
            System.out.println("Take item from inventory to cursor: " + current);
            // clear inventory out of worth
            clearInvOutOfWorth(e.getInventory().getContents());
        }
        if ((current == null || current.getType().equals(Material.AIR)) && (cursor != null || !(cursor.getType().equals(Material.AIR)))) {
            //place item from cursor to inv
            System.out.println("Placing item from cursor to inventory: " + cursor);
            //handler.updateStack((Player) e.getWhoClicked(), cursor, slot);
            //recalculate worth all inv
            fillInvWithWorth(e.getInventory().getContents());
        }
//        if (current != null && current.getType() != Material.AIR) {
//            System.out.println("Before meta: " + current.getItemMeta().getLore());
//            handler.updateStack((Player) e.getWhoClicked(), current, slot);
//            System.out.println("After meta: " + current.getItemMeta().getLore());
//        }
    }

//    @EventHandler
//    public void onInventoryDrag(InventoryDragEvent e) {
//        System.out.println("----------");
//        System.out.println("triggers drag");
//        System.out.println(e.getCursor());
//        System.out.println(e.getOldCursor());
//        System.out.println("----------");
//        ItemStack cursor = e.getOldCursor();
//        if (cursor != null && cursor.getType() != Material.AIR) {
//            for (int slot : e.getInventorySlots()) {
//                handler.updateStack((Player) e.getWhoClicked(), cursor, slot);
//            }
//        }
//    }

//    @EventHandler
//    public void onItemDrop(PlayerDropItemEvent e) {
//        System.out.println("----------");
//        System.out.println("triggers drop event");
//        System.out.println(e.getItemDrop());
//        System.out.println(e.getItemDrop().getItemStack());
//        System.out.println("----------");
//        ItemStack dropped = e.getItemDrop().getItemStack();
//        int slot = e.getPlayer().getInventory().getHeldItemSlot();
//        handler.updateStack(e.getPlayer(), dropped, slot);
//        e.getPlayer().updateInventory();
//    }

//    @EventHandler
//    public void onItemPickup(EntityPickupItemEvent e) {
//        if (e.getEntity() instanceof Player player) {
//            ItemStack picked = e.getItem().getItemStack();
//            handler.updateStack(player, picked, -1);
//        }
//    }
    public void fillInvWithWorth(ItemStack[] contents){
        for (ItemStack content : contents) {
            ItemMeta itemMeta = content.getItemMeta();
            List<String> lore = itemMeta.getLore();
            if (lore==null){
                lore = new ArrayList<>();
            }
            //itemMeta.setLore();
            lore.add("worth:" + handler.getWorth(content));
        }
    }
    public void clearInvOutOfWorth(ItemStack[] contents){
        for (ItemStack content : contents) {
            ItemMeta itemMeta = content.getItemMeta();
            if (itemMeta != null){
                List<String> lore = itemMeta.getLore();
                if (lore!=null){
                    lore.removeIf(line -> line.startsWith("worth"));
                }
            }
        }
    }
}

