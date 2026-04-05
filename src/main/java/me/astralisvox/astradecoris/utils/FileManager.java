package me.astralisvox.astradecoris.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;

import me.astralisvox.astradecoris.AstraDecoris;
import me.astralisvox.astralibs.Utilities;
import me.astralisvox.astralibs.configs.ConfigCreator;
import me.astralisvox.astralibs.configs.ConfigUpdater;

public class FileManager {
    private final AstraDecoris plugin;
    private final ConfigCreator configFile;
    private final ConfigCreator messageFile;
    private final ConfigCreator chatlogFile;

    private final static String CONFIG_VERSION = "1.0";
    private final static String MESSAGES_VERSION = "1.0";

    public FileManager(AstraDecoris plugin) {
        this.plugin = plugin;
        this.configFile = new ConfigCreator("config.yml");
        this.messageFile = new ConfigCreator("messages.yml");
        this.chatlogFile = new ConfigCreator("chatlog.log");
    }

    public void setupFiles() {
        getChatlogFile().createConfig();
        getConfigFile().createConfig();
        getMessageFile().createConfig();
    }

    public void configUpdater() {
        Utilities.logInfo(true, "Attempting to update the config files...");

        try {
            updateFile(configFile, "config.yml", CONFIG_VERSION);
            updateFile(messageFile, "messages.yml", MESSAGES_VERSION);
            plugin.onReload();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void updateFile(ConfigCreator creator, String fileName, String expectedVersion) throws IOException {
        String currentVersion = creator.getConfig().getString("Config_Version");

        if(currentVersion == null) {
            Utilities.logInfo(true, "Update checking skipped for " + fileName);
            return;
        }

        if (currentVersion.equals(expectedVersion)) {
            Utilities.logInfo(true, "The " + fileName + " is up to date.");
            return;
        }

        creator.getConfig().set("Config_Version", expectedVersion);
        creator.saveConfig();

        ConfigUpdater.update(Utilities.getInstance(), fileName, creator.getFile(), Collections.emptyList());
        creator.saveConfig();
        Utilities.logInfo(true, "The " + fileName + " has been updated.");
    }

    public void reloadFiles() {
        getConfigFile().reloadConfig();
        getMessageFile().reloadConfig();
    }

    public ConfigCreator getChatlogFile() {
        return chatlogFile;
    }

    public ConfigCreator getConfigFile() {
        return configFile;
    }

    public ConfigCreator getMessageFile() {
        return messageFile;
    }
}
