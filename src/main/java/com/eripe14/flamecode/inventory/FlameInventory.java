package com.eripe14.flamecode.inventory;

import com.eripe14.flamecode.config.implementation.InventoryConfig;
import com.eripe14.flamecode.inventory.item.InventoryItem;
import com.eripe14.flamecode.util.legacy.LegacyItemColorProcessor;
import dev.triumphteam.gui.components.GuiAction;
import dev.triumphteam.gui.guis.Gui;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.Arrays;
import java.util.List;

public class FlameInventory {

    private static final MiniMessage MINI_MESSAGE = MiniMessage.builder()
            .postProcessor(new LegacyItemColorProcessor())
            .build();

    private final InventoryConfig inventoryConfig;

    public FlameInventory(InventoryConfig inventoryConfig) {
        this.inventoryConfig = inventoryConfig;
    }

    public void openInventory(Player player) {
        InventoryConfig inventory = this.inventoryConfig;

        Gui gui = Gui.gui()
                .title(color(inventory.inventoryName))
                .rows(inventory.inventoryRows)
                .disableAllInteractions()
                .create();

        this.setItem(gui, inventory.quitItem, event -> player.closeInventory());

        for (InventoryItem item : this.inventoryConfig.items) {
            this.setItem(gui, item, (event) -> {
                player.sendMessage("Clicked " + item.getItemName());
            });
        }

        gui.open(player);
    }

    private void setItem(Gui gui, InventoryItem item, GuiAction<InventoryClickEvent> action) {
        gui.setItem(item.getItemSlotInGui(), item
                .getItemBuilder()
                .asGuiItem(action));
    }

    private Component color(String text) {
        return MINI_MESSAGE.deserialize(text);
    }

    private List<Component> color(String... lore) {
        return Arrays.stream(lore).map(MINI_MESSAGE::deserialize).toList();
    }
}
