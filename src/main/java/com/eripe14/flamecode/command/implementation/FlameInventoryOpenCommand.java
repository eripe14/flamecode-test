package com.eripe14.flamecode.command.implementation;

import com.eripe14.flamecode.config.implementation.InventoryConfig;
import com.eripe14.flamecode.inventory.FlameInventory;
import dev.rollczi.litecommands.command.execute.Execute;
import dev.rollczi.litecommands.command.permission.Permission;
import dev.rollczi.litecommands.command.section.Section;
import org.bukkit.entity.Player;

@Section(route = "flamecode")
@Permission("flamecode.inventory")
public class FlameInventoryOpenCommand {

    private final InventoryConfig inventoryConfig;

    public FlameInventoryOpenCommand(InventoryConfig inventoryConfig) {
        this.inventoryConfig = inventoryConfig;
    }

    @Execute
    public void execute(Player player) {
        FlameInventory flameInventory = new FlameInventory(this.inventoryConfig);

        flameInventory.openInventory(player);
    }
}
