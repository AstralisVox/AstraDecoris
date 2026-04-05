package me.astralisvox.astradecoris.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.astralisvox.astradecoris.AstraDecoris;

public class ChatLogger implements Listener {
    private final AstraDecoris plugin;
    private final File chatLogFile;

    public ChatLogger(AstraDecoris plugin) {
        this.plugin = plugin;
        this.chatLogFile = plugin.getFileManager().getChatlogFile().getFile();
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        if(!plugin.getFileManager().getConfigFile().getConfig().getBoolean("Chat_Log.Enabled")) return;

        String player = event.getPlayer().getName();
        String message = event.getMessage();

        LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(System.currentTimeMillis()), ZoneId.systemDefault());
        String dateTimeFormatted = dateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));

        String line = "[" + dateTimeFormatted + "] " + player + ": " + message;

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(chatLogFile, true))) {
            writer.write(line);
            writer.newLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
