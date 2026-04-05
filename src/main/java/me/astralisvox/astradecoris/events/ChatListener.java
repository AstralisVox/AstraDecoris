package me.astralisvox.astradecoris.events;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.astralisvox.astradecoris.AstraDecoris;

public class ChatListener implements Listener {
    private final AstraDecoris plugin;
    private final FileConfiguration configFile;

    public ChatListener(final AstraDecoris plugin) {
        this.plugin = plugin;
        this.configFile = plugin.getFileManager().getConfigFile().getConfig();
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerChat(AsyncPlayerChatEvent chatEvent) {
        final Player player = chatEvent.getPlayer();
        String rawMessage = chatEvent.getMessage();
    }
}
