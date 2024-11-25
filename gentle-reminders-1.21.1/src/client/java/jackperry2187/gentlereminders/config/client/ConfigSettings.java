package jackperry2187.gentlereminders.config.client;

import jackperry2187.gentlereminders.GentleReminders;
import jackperry2187.gentlereminders.util.Message;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Environment(value = EnvType.CLIENT)
public class ConfigSettings {
    private static boolean isInitialized = false;

    public static final Path configPath = FabricLoader.getInstance().getConfigDir();
    public static final Path modConfigFile = configPath.resolve(GentleReminders.MOD_ID + "-config.toml");

    public static int configVersion;
    // TODO: private static boolean skipConfigVersionCheck;

    public static boolean enabled;
    public static int ticksBetweenMessages;

    public static List<Message> messages = new ArrayList<>();
    public static int highestMessageID = 0;

    public static String displayStyle = "chatMessage"; // defaultToast, customToast, chatMessage

    private static final boolean isDebug = false;
    private static final int debugTicksBetweenMessages = 20 * 7; // 7 seconds

    public static void initialize() {
        if(isInitialized) {
            GentleReminders.LOGGER.error("ConfigSettings have already been initialized!");
            return;
        }

        if(!Files.exists(modConfigFile)) {
            GentleReminders.LOGGER.error("Config file does not exist!");
            throw new RuntimeException("Config file does not exist!");
        }

        readConfigFile();
        if(isDebug) ticksBetweenMessages = debugTicksBetweenMessages;

        messages.sort(Comparator.comparingInt(m -> m.ID));
        highestMessageID = messages.get(messages.size() - 1).ID;

        isInitialized = true;
        if(isDebug) logConfigSettings();
    }

    private static void logConfigSettings() {
        GentleReminders.LOGGER.info("Config Version: {}", configVersion);
        // TODO: GentleReminders.LOGGER.info("Skip Config Version Check: {}", skipConfigVersionCheck);
        GentleReminders.LOGGER.info("Enabled: {}", enabled);
        GentleReminders.LOGGER.info("Display Style: {}", displayStyle);
        GentleReminders.LOGGER.info("Time Between Messages: {} ticks, {} minutes", ticksBetweenMessages, ticksBetweenMessages / 20 / 60);
//        GentleReminders.LOGGER.info("Messages:");
//        for(Message message : messages) {
//            GentleReminders.LOGGER.info("ID: {}", message.ID);
//            GentleReminders.LOGGER.info("Title: {}", message.Title.getString());
//            GentleReminders.LOGGER.info("Message: {}", message.Description.getString());
//            GentleReminders.LOGGER.info("Enabled: {}", message.Enabled);
//        }
    }

    private static void readConfigFile() {
        try {
            boolean isReadingMessages = false;
            for(String line : Files.readAllLines(modConfigFile)) {
                if (line.startsWith("#")) continue; // skip comments
                if (line.startsWith("configVersion=")) {
                    configVersion = Integer.parseInt(line.split("=")[1]);
                }
                // TODO: else if (line.startsWith("skipConfigVersionCheck=")) { skipConfigVersionCheck = Boolean.parseBoolean(line.split("=")[1]); }
                else if(line.startsWith("enabled=")) {
                    enabled = Boolean.parseBoolean(line.split("=")[1]);
                } else if(line.startsWith("displayStyle=")) {
                    String got = line.split("=")[1].strip().replace("\"", "");
                    switch(got) {
                        case "default":
                            displayStyle = "defaultToast";
                            break;
                        case "chat":
                            displayStyle = "chatMessage";
                            break;
                        default:
                            GentleReminders.LOGGER.error("Invalid display style: {}! Should be default or chat, setting to default", got);
                            displayStyle = "defaultToast";
                            break;
                    }
                } else if(line.startsWith("timeBetweenMessages=")) {
                    ticksBetweenMessages = Integer.parseInt(line.split("=")[1]) * 20 * 60; // convert minutes to ticks
                } else if(line.startsWith("messages=[")) {
                    isReadingMessages = true;
                } else if (isReadingMessages) {
                    if(line.startsWith("]")) {
                        isReadingMessages = false;
                    }
                    else {
                        messages.add(parseMessage(line));
                    }
                }
            }
        } catch (Exception e) {
            GentleReminders.LOGGER.error("Failed to read config file!");
            throw new RuntimeException(e);
        }
    }

    private static Message parseMessage(String line) {
        // line will be in format of:
        // {id=#, title="String which does not contain a comma", message="String which does not contain a comma", enabled=boolean, titleColor="String", messageColor="String"},
        // parse the line and create a new Message object
        String[][] parts = Arrays.stream(line.split(",")).map(s -> s.trim().replace("\"", "").split("=")).toArray(String[][]::new);
        int id = Integer.parseInt(parts[0][1]);
        String title = parts[1][1];
        String message = parts[2][1];
        boolean enabled = Boolean.parseBoolean(parts[3][1]);
        String titleColor = parts[4][1];
        String messageColor = parts[5][1];

        Text titleText = MutableText.of(Text.of(title).getContent()).formatted(Formatting.byName(titleColor));
        Text messageText = MutableText.of(Text.of(message).getContent()).formatted(Formatting.byName(messageColor));

        // create a new Message object and add it to the messages list
        return new Message(id, titleText, messageText, enabled);
    }
}
