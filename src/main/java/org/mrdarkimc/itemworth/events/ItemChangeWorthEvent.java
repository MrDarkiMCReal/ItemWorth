package org.mrdarkimc.itemworth.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public class ItemChangeWorthEvent extends Event {

    private int slotID;
    private ItemStack stack;

    public ItemChangeWorthEvent(int slotID, ItemStack stack) {
        this.slotID = slotID;
        this.stack = stack;
    }

    private static final HandlerList HANDLERS = new HandlerList();

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }
}
