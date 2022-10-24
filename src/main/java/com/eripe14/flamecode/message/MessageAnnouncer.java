package com.eripe14.flamecode.message;

import net.kyori.adventure.platform.AudienceProvider;
import net.kyori.adventure.text.minimessage.MiniMessage;

import java.util.List;
import java.util.UUID;

public final class MessageAnnouncer {

    private final AudienceProvider audienceProvider;
    private final MiniMessage miniMessage;

    public MessageAnnouncer(AudienceProvider audienceProvider, MiniMessage miniMessage) {
        this.audienceProvider = audienceProvider;
        this.miniMessage = miniMessage;
    }

    public void sendMessage(UUID uuid, List<String> message) {
        for (String text : message) {
            this.audienceProvider.player(uuid).sendMessage(this.miniMessage.deserialize(text));
        }
    }

    public void sendMessage(UUID uuid, String message) {
        this.audienceProvider.player(uuid).sendMessage(this.miniMessage.deserialize(message));
    }

    public void sendConsoleMessage(String message) {
        this.audienceProvider.console().sendMessage(this.miniMessage.deserialize(message));
    }

    public void sendActionBar(UUID uuid, String message) {
        this.audienceProvider.player(uuid).sendActionBar(this.miniMessage.deserialize(message));
    }


}
