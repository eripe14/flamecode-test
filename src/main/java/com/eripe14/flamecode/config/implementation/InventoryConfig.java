package com.eripe14.flamecode.config.implementation;

import com.eripe14.flamecode.config.ReloadableConfig;
import com.eripe14.flamecode.inventory.item.InventoryItem;
import com.google.common.collect.ImmutableList;
import net.dzikoysk.cdn.entity.Description;
import net.dzikoysk.cdn.source.Resource;
import net.dzikoysk.cdn.source.Source;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;

import java.io.File;
import java.util.List;

public class InventoryConfig implements ReloadableConfig {

    @Description(" # Ustawienia inventory.")
    public String inventoryName = "&8&lFlameCode";

    public int inventoryRows = 6;

    @Description(" # Ustawienia itemów w inventory.")
    public List<InventoryItem> items = new ImmutableList.Builder<InventoryItem>()
            .add(new InventoryItem("&e&lInfo",
                    List.of(" ", "&8Test 1", " "),
                    List.of(ItemFlag.HIDE_ATTRIBUTES),
                    Material.DIAMOND, 0, 1))
            .add(new InventoryItem("&e&lInfo",
                    List.of(" ", "&8Test 2", " "),
                    List.of(ItemFlag.HIDE_ATTRIBUTES),
                    Material.GOLD_INGOT,
                    1, 1))
            .add(new InventoryItem("&e&lInfo",
                    List.of(" ", "&8Test 3", " "),
                    List.of(ItemFlag.HIDE_ATTRIBUTES),
                    Material.IRON_INGOT,
                    2, 1))
            .build();

    public InventoryItem quitItem = new InventoryItem("&c&lWyjście",
            List.of(" ", "&8Kliknij, aby wyjść.", " "),
            List.of(ItemFlag.HIDE_ATTRIBUTES),
            Material.BARRIER,
            49, 1);

    @Override
    public Resource resource(File folder) {
        return Source.of(folder, "inventory.yml");
    }
}
