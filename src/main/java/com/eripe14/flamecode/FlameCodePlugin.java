package com.eripe14.flamecode;

import com.eripe14.flamecode.command.argument.NumberArgument;
import com.eripe14.flamecode.command.handler.InvalidUsage;
import com.eripe14.flamecode.command.handler.PermissionMessage;
import com.eripe14.flamecode.command.implementation.FlameInventoryOpenCommand;
import com.eripe14.flamecode.config.ConfigManager;
import com.eripe14.flamecode.config.implementation.InventoryConfig;
import com.eripe14.flamecode.config.implementation.MessageConfig;
import com.eripe14.flamecode.inventory.FlameInventory;
import com.eripe14.flamecode.message.MessageAnnouncer;
import com.eripe14.flamecode.util.legacy.LegacyColorProcessor;
import com.google.common.base.Stopwatch;
import dev.rollczi.litecommands.LiteCommands;
import dev.rollczi.litecommands.bukkit.LiteBukkitFactory;
import dev.rollczi.litecommands.bukkit.tools.BukkitOnlyPlayerContextual;
import dev.rollczi.litecommands.bukkit.tools.BukkitPlayerArgument;
import net.kyori.adventure.platform.AudienceProvider;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.TimeUnit;

public class FlameCodePlugin extends JavaPlugin {

    private ConfigManager configManager;

    private MessageConfig messageConfig;
    private InventoryConfig inventoryConfig;

    private AudienceProvider audienceProvider;
    private MiniMessage miniMessage;

    private MessageAnnouncer messageAnnouncer;

    private LiteCommands<CommandSender> liteCommands;

    @Override
    public void onEnable() {
        Stopwatch started = Stopwatch.createStarted();

        this.configManager = new ConfigManager(this.getDataFolder());

        this.messageConfig = this.configManager.load(new MessageConfig());
        this.inventoryConfig = this.configManager.load(new InventoryConfig());

        this.audienceProvider = BukkitAudiences.create(this);
        this.miniMessage = MiniMessage.builder()
                .postProcessor(new LegacyColorProcessor())
                .build();

        this.messageAnnouncer = new MessageAnnouncer(this.audienceProvider, this.miniMessage);

        this.liteCommands = LiteBukkitFactory.builder(this.getServer(), "flamecode-custom-inventory")
                .argument(Player.class, new BukkitPlayerArgument<>(this.getServer(), this.messageConfig.wrongUsage.cantFindPlayer))
                .argument(int.class, new NumberArgument(this.messageConfig))

                .contextualBind(Player.class, new BukkitOnlyPlayerContextual<>(this.messageConfig.wrongUsage.onlyForPlayer))

                .invalidUsageHandler(new InvalidUsage(this.messageAnnouncer, this.messageConfig))
                .permissionHandler(new PermissionMessage(this.messageConfig, this.messageAnnouncer))

                .commandInstance(new FlameInventoryOpenCommand(this.inventoryConfig))

                .register();

        long millis = started.elapsed(TimeUnit.MILLISECONDS);
        this.getLogger().info("Successfully loaded FlameCode-CustomInventory in " + millis + "ms");
    }

    public ConfigManager getConfigManager() {
        return this.configManager;
    }

    public MessageConfig getMessageConfig() {
        return this.messageConfig;
    }

    public InventoryConfig getInventoryConfig() {
        return this.inventoryConfig;
    }

    public AudienceProvider getAudienceProvider() {
        return this.audienceProvider;
    }

    public MiniMessage getMiniMessage() {
        return this.miniMessage;
    }

    public MessageAnnouncer getMessageAnnouncer() {
        return this.messageAnnouncer;
    }

    public LiteCommands<CommandSender> getLiteCommands() {
        return this.liteCommands;
    }

}
