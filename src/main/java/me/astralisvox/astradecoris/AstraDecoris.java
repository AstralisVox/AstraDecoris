package me.astralisvox.astradecoris;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import me.astralisvox.astradecoris.utils.FileManager;
import me.astralisvox.astradecoris.utils.MessageHandler;
import me.astralisvox.astradecoris.utils.UpdateChecker;
import me.astralisvox.astralibs.Utilities;
import me.astralisvox.astralibs.chat.AudienceManager;
import me.clip.placeholderapi.PlaceholderAPI;

public final class AstraDecoris extends JavaPlugin {
    private FileManager fileManager;
    private MessageHandler messageHandler;
    //private UpdateChecker updateChecker;

    @Override
    public void onEnable() {
        Utilities.setInstance(this);

        Utilities.logInfo(false,
             "___ ______",
            "/ _ \\|  _  \\",
            "/ /_\\ \\ | | |  AstraDecoris v" + this.getDescription().getVersion() + " By AstralisVox",
            "|  _  | | | |   Running on version: " + Bukkit.getName() + " " + Bukkit.getVersion(),
            "| | | | |/ /    Discord: https://discord.gg/rCEcVaybTC Github: https://github.com/AstralisVox/AstraDecoris",
            "\\_| |_/___/"
        );

        fileManager = new FileManager(this);
        getFileManager().setupFiles();
        getFileManager().configUpdater();
        AudienceManager.init(this);

        //updateChecker = new UpdateChecker(this);
        //getUpdateChecker().consoleUpdates();

        messageHandler = new MessageHandler(getFileManager().getMessageFile().getConfig());

    }

    @Override
    public void onDisable() {
        AudienceManager.shutdown();
    }

    public void onReload() {
        getFileManager().reloadFiles();
    }

    private void checkSoftDepends() {
        // Check for PlaceholderAPI
        if(Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            //setupPlaceholders(this);
        } else {
            Utilities.logInfo(true, "This plugin requires PlaceholderAPI to be installed if you are",
                    " wanting to use placeholders in the chat formats.");
        }
    }

    public FileManager getFileManager() {
        return fileManager;
    }

    public MessageHandler getMessageHandler() {
        return messageHandler;
    }

//    public UpdateChecker getUpdateChecker() {
//        return updateChecker;
//    }

    //    private void registerBStats() {
//        int pluginID = 30440;
//
//        Metrics metrics = new Metrics(this, pluginID);
//        metrics.addCustomChart(new MultiLineChart("players_and_servers", () -> {
//            Map<String, Integer> valueMap = new HashMap<>();
//            valueMap.put("servers", 1);
//            valueMap.put("players", Bukkit.getOnlinePlayers().size());
//            return valueMap;
//        }));
//    }
}
