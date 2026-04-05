package me.astralisvox.astradecoris.utils;

import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;

import me.astralisvox.astradecoris.AstraDecoris;
import me.astralisvox.astralibs.PluginUpdater;
import me.astralisvox.astralibs.Utilities;

public class UpdateChecker {
    private AstraDecoris plugin;
    private MessageHandler messageHandler;

    public UpdateChecker(AstraDecoris plugin) {
        this.plugin = plugin;
        this.messageHandler = plugin.getMessageHandler();
    }

    public void consoleUpdates() {
        if(!plugin.getFileManager().getConfigFile().getConfig().getBoolean("Update_Notify")) return;

        new PluginUpdater(plugin, 1234).getVersion(version ->{
            int spigotVersion = Integer.parseInt(version.replace(".", ""));
            int pluginVersion = Integer.parseInt(plugin.getDescription().getVersion().replace(".", ""));

            if(pluginVersion >= spigotVersion) {
                Utilities.logInfo(true, "You are running the latest version of AstraDecoris");
            } else {
                PluginDescriptionFile pdf = plugin.getDescription();
                Utilities.logWarning(true,
                        "A new version of " + pdf.getName() + " is available!",
                        "Current Version: " + pdf.getVersion() + " > New Version: " + version,
                        "Grab it here: https://www.spigotmc.org/resources/astradecoris.xxxxx/"
                );
            }
        });
    }

    public void playerUpdates(Player player) {
        if(!plugin.getFileManager().getConfigFile().getConfig().getBoolean("Update_Notify")) return;

        new PluginUpdater(plugin, 133245).getVersion(version -> {
            int spigotVersion = Integer.parseInt(version.replace(".", ""));
            int pluginVersion = Integer.parseInt(plugin.getDescription().getVersion().replace(".", ""));

            if(pluginVersion >= spigotVersion) {
                Utilities.message(player, messageHandler.get("Updates.Up_To_Date", "&aYou are running the latest version of AstraVisus."));
            } else {
                PluginDescriptionFile pdf = plugin.getDescription();
                Utilities.message(player,
                        messageHandler.getPrefix() + "&aA new version of " + pdf.getName() + "&a is available!",
                        messageHandler.getPrefix() + "&aCurrent Version: " + pdf.getVersion() + "&a > New Version: " + version,
                        messageHandler.getPrefix() + "&aGrab it here: https://www.spigotmc.org/resources/astradecoris.xxxxx/"
                );
            }
        });
    }
}
