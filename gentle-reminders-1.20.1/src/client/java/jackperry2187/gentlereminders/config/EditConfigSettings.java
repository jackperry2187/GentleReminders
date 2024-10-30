package jackperry2187.gentlereminders.config;

import jackperry2187.gentlereminders.util.Message;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.List;

import static jackperry2187.gentlereminders.config.ConfigSettings.modConfigFile;

@Environment(value = EnvType.CLIENT)
public class EditConfigSettings {
    public static boolean setFileValue(String key, String value) {
        try {
            boolean hit = false;
            List<String> lines = Files.readAllLines(modConfigFile);
            for(String line : lines) {
                if(!line.strip().startsWith(key + "=")) continue;
                hit = true;
                lines.set(lines.indexOf(line), key + "=" + value);
                break;
            }
            Files.write(modConfigFile, lines, StandardOpenOption.TRUNCATE_EXISTING);
            return hit;
        } catch (IOException e) {
            return false;
        }
    }

    public static boolean removeFileLine(String key, String value) {
        try {
            boolean hit = false;
            List<String> lines = Files.readAllLines(modConfigFile);
            for(String line : lines) {
                if(!line.strip().startsWith(key + "=" + value)) continue;
                hit = true;
                lines.remove(line);
                break;
            }
            Files.write(modConfigFile, lines, StandardOpenOption.TRUNCATE_EXISTING);
            return hit;
        } catch (IOException e) {
            return false;
        }
    }

    public static boolean setMessageValue(int id, String key, String value) {
        // messages are formatted like {id=#, title="String", message="String", enabled=boolean, titleColor="String", messageColor="String"},
        try {
            boolean hit = false;
            List<String> lines = Files.readAllLines(modConfigFile);
            for(String line : lines) {
                if(!line.strip().startsWith("{id=" + id)) continue;
                hit = true;

                String[] parts = line.split(",");
                for(int i = 0; i < parts.length; i++) {
                    if(!parts[i].strip().startsWith(key + "=")) continue;
                    if(i != 0) parts[i] = " " + key + "=" + value;
                    else parts[i] = "{" + key + "=" + value;
                    break;
                }
                String newLine = String.join(",", parts) + ",";
                lines.set(lines.indexOf(line), newLine);
            }
            Files.write(modConfigFile, lines, StandardOpenOption.TRUNCATE_EXISTING);
            return hit;
        } catch (IOException e) {
            return false;
        }
    }

    public static boolean setFileMessage(int id, Message newMessage) {
        try {
            boolean hit = false;
            List<String> lines = Files.readAllLines(modConfigFile);
            for(String line : lines) {
                if(!line.strip().startsWith("{id=" + id)) continue;
                hit = true;
                lines.set(lines.indexOf(line), newMessage.toString());
                break;
            }
            Files.write(modConfigFile, lines, StandardOpenOption.TRUNCATE_EXISTING);
            return hit;
        } catch (IOException e) {
            return false;
        }
    }

    public static boolean addFileMessage(Message newMessage) {
        try {
            List<String> lines = Files.readAllLines(modConfigFile);
            for(String line : lines) {
                if(!line.strip().startsWith("]")) continue;
                lines.set(lines.indexOf(line), newMessage.toString());
                break;
            }
            lines.add("]");
            Files.write(modConfigFile, lines, StandardOpenOption.TRUNCATE_EXISTING);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
