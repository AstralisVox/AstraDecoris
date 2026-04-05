package me.astralisvox.astradecoris.events;

import java.util.EventListener;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.astralisvox.astradecoris.AstraDecoris;

public class PlayerListener implements Listener {
    private final AstraDecoris pluginInstance;
    //private final FileConfiguration configFile;

    public PlayerListener(final AstraDecoris pluginInstance) {
        this.pluginInstance = pluginInstance;

    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        event.setCancelled(true);

        String formatted = "<" + event.getPlayer().getName() + ">" + event.getMessage();
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.sendMessage(formatted);
        }
    }
}
