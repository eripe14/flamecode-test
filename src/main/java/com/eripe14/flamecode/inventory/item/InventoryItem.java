package com.eripe14.flamecode.inventory.item;

import com.eripe14.flamecode.util.legacy.LegacyItemColorProcessor;
import dev.triumphteam.gui.builder.item.ItemBuilder;
import net.dzikoysk.cdn.entity.Contextual;
import net.dzikoysk.cdn.entity.Exclude;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

@Contextual
public class InventoryItem {

    @Exclude
    private static final MiniMessage MINI_MESSAGE = MiniMessage.builder()
            .postProcessor(new LegacyItemColorProcessor())
            .build();

    private String itemName;

    private List<String> itemLore;

    private List<ItemFlag> itemFlags;

    private Material itemMaterial;

    private int itemAmount;

    private int itemSlotInGui;

    public InventoryItem(String itemName, List<String> itemLore, List<ItemFlag> itemFlags, Material itemMaterial, int itemSlotInGui, int itemAmount) {
        this.itemName = itemName;
        this.itemLore = itemLore;
        this.itemFlags = itemFlags;
        this.itemMaterial = itemMaterial;
        this.itemSlotInGui = itemSlotInGui;
        this.itemAmount = itemAmount;
    }

    public InventoryItem(String itemName, List<String> itemLore, List<ItemFlag> itemFlags, Material itemMaterial) {
        this.itemName = itemName;
        this.itemLore = itemLore;
        this.itemFlags = itemFlags;
        this.itemMaterial = itemMaterial;
    }

    public InventoryItem(ItemStack itemStack) {
        ItemMeta itemMeta = itemStack.getItemMeta();

        if (itemMeta == null) {
            return;
        }

        this.itemName = itemMeta.getDisplayName();
        this.itemLore = itemMeta.getLore() == null ? new ArrayList<>() : itemMeta.getLore();
        this.itemFlags = new ArrayList<>(itemMeta.getItemFlags());
        this.itemMaterial = itemStack.getType();
        this.itemAmount = itemStack.getAmount();
    }

    public InventoryItem() {

    }

    public List<String> getItemLore() {
        return this.itemLore;
    }

    public List<ItemFlag> getItemFlags() {
        return this.itemFlags;
    }

    public Material getItemMaterial() {
        return this.itemMaterial;
    }

    public int getItemAmount() {
        return this.itemAmount;
    }

    public int getItemSlotInGui() {
        return this.itemSlotInGui;
    }

    public String getItemName() {
        return this.itemName;
    }

    public ItemBuilder getItemBuilder() {
        List<Component> lore = this.itemLore.stream()
                .map(MINI_MESSAGE::deserialize)
                .toList();

        return ItemBuilder.from(this.itemMaterial)
                .name(MINI_MESSAGE.deserialize(this.itemName))
                .lore(lore)
                .amount(this.itemAmount)
                .flags(this.itemFlags.toArray(new ItemFlag[0]));
    }
}