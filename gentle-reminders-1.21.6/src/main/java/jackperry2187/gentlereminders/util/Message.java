package jackperry2187.gentlereminders.util;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import static jackperry2187.gentlereminders.config.DefaultSettings.*;

@Environment(value = EnvType.CLIENT)
public class Message {
    private static int messageCount = 0;
    public int ID;
    public Text Title;
    public Text Description;
    public boolean Enabled;

    public Message(int id, Text title, Text description, boolean enabled) {
        ID = id;
        Title = title;
        Description = description;
        Enabled = enabled;
    }

    public Message(Text title, Text description, boolean enabled) {
        ID = messageCount++;
        Title = title;
        Description = description;
        Enabled = enabled;
    }

    public String toString() {
        String titleColor = Title.getStyle().getColor().getName();
        String messageColor = Description.getStyle().getColor().getName();

        return " {id=" + ID + ", title=\"" + Title.getString() + "\", message=\"" + Description.getString() + "\", enabled=" + Enabled + ", titleColor=\"" + titleColor + "\", messageColor=\"" + messageColor + "\"},";
    }

    public static Message generateSimpleMessage(String message) {
        return new Message(
                MutableText.of(Text.of(defaultTitle).getContent()).formatted(defaultTitleColor),
                MutableText.of(Text.of(message).getContent()).formatted(defaultMessageColor),
                true
        );
    }

    public static Message generateUniqueMessage(String title, String message, boolean enabled, Formatting titleColor, Formatting messageColor) {
        return new Message(
                MutableText.of(Text.of(title).getContent()).formatted(titleColor),
                MutableText.of(Text.of(message).getContent()).formatted(messageColor),
                enabled
        );
    }
}
