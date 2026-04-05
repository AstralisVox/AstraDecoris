package me.astralisvox.astradecoris.utils;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import me.astralisvox.astralibs.Utilities;
import me.clip.placeholderapi.PlaceholderAPI;

public class MessageHandler {
    private final FileConfiguration messagesFile;
    private final Set<String> missingMessage = new HashSet<>();

    public MessageHandler(FileConfiguration messagesFile) {
        this.messagesFile = messagesFile;
    }

    public String get(String path, String fallback) {
        String message = messagesFile.getString(path);

        if(message == null) {
            getErrorMessage(path);
            return getPrefix() + fallback;
        }

        if(message.isEmpty()) {
            return "";
        }

        return getPrefix() + message;
    }

    public String getPrefix() {
        if(messagesFile.getString("Prefix") == null) {
            getErrorMessage("Prefix");
            return "#8c8c8c[#2b9bbf&lAD#8c8c8c]" + " ";
        }
        if(Objects.requireNonNull(messagesFile.getString("Prefix")).equalsIgnoreCase("none")) {
            return "";
        }
        return messagesFile.getString("Prefix");
    }

    public static String parsePlaceholders(Player player, String text) {
        if(text == null) return null;

        String parsed = PlaceholderAPI.setPlaceholders(player, text);
        parsed = PlaceholderAPI.setBracketPlaceholders(player, parsed);
        return parsed;
    }

    private void getErrorMessage(final String message) {
        if(missingMessage.contains(message)) return;
        missingMessage.add(message);

        Utilities.logInfo(true,
                "There was an error getting the " + message + " message from the " + messagesFile.getName() + ".",
                "I have set a fallback message to take it's place until the issue is fixed.",
                "To resolve this, please locate " + message + " in the " + messagesFile.getName() + " and fix the issue."
        );
    }
}
