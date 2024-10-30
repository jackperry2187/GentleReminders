package jackperry2187.gentlereminders.config;

import jackperry2187.gentlereminders.util.Message;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static jackperry2187.gentlereminders.config.DefaultSettings.*;

@Environment(value = EnvType.CLIENT)
public class ConfigLines {
    public static List<String> getBaseConfigLines() {
        List<String> initialList = new ArrayList<>(Arrays.asList(
                "### Welcome to the Gentle Reminders Config",
                "### This file is used to configure the Gentle Reminders mod to your liking",
                "# The config version is used to ensure that the config file is up to date",
                "# If the config version is different from the current version, the config file will be overwritten",
                "configVersion=" + configVersion,
                "###",
// TODO: implement this
//                "# If this is set to true, the configVersion will not be checked and this file will never be overwritten",
//                "# If you are adding your own messages, it is recommended to set this to true",
//                "# If you are updating the mod and want the most recent list of messages, it is recommended to set this to false",
//                "skipConfigVersionCheck=false",
//                "###",
                "# If this is set to false, the mod will never send messages",
                "enabled=" + enabled,
                "###",
                "# The time between messages in minutes, defaults to 10 minutes",
                "# Accepts only whole numbers of minutes",
                "timeBetweenMessages=" + timeBetweenMessages,
                "###",
                "# The messages that will be sent to the player",
                "# These messages are chosen randomly every time a message is sent, and will try not to repeat the same message twice in a row",
                "# The ID should be unique for each message, and should be a whole number, but the actual ID doesn't matter",
                "# Except for the message with ID 0, which will be sent first when a player joins a world",
                "# If the list is empty, the mod will not send any messages",
                "messages=["
        ));
        initialList.addAll(getMessagesLines(defaultMessages));
        initialList.add("]");

        return initialList;
    }

    public static List<String> getMessagesLines(List<Message> messages) {
        List<String> messageLines = new ArrayList<>();
        messageLines.addAll(messages.stream().map(Message::toString).toList());
        return messageLines;
    }
}
