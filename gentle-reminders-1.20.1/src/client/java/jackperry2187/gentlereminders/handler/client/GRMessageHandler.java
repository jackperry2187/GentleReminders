package jackperry2187.gentlereminders.handler.client;

import jackperry2187.gentlereminders.GentleReminders;
import jackperry2187.gentlereminders.config.client.ConfigSettings;
import jackperry2187.gentlereminders.util.Message;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

@Environment(value = EnvType.CLIENT)
public class GRMessageHandler {
    private static int lastMessageIndex = -1;
    private static int numberOfMessagesSent = 0;
    private static final List<Integer> idsOfUnsentMessages = new ArrayList<>();

    public static @Nullable Message getInitialMessage() {
        if(lastMessageIndex != -1) {
            GentleReminders.LOGGER.error("Initial message has already been sent!");
            return null;
        }
        Message initialMessage = ConfigSettings.messages.get(0);

        lastMessageIndex = 0;

        return initialMessage;
    }

    public static @Nullable Message getRandomMessage() {
        if(lastMessageIndex == -1) {
            return getInitialMessage();
        }

        if(numberOfMessagesSent == ConfigSettings.messages.size() - 1) {
            numberOfMessagesSent = 0;
            idsOfUnsentMessages.clear();
        }

        if(idsOfUnsentMessages.isEmpty()) resetIdsOfUnsentMessages();

        // generate a random index to get the next message
        int nextMessageIndex = (int) (Math.random() * (idsOfUnsentMessages.size() - 1));
        Message nextMessage = ConfigSettings.messages.stream().filter(message -> message.ID == idsOfUnsentMessages.get(nextMessageIndex)).findFirst().orElse(null);

        numberOfMessagesSent++;
        idsOfUnsentMessages.remove(nextMessageIndex);
        lastMessageIndex = nextMessageIndex;

        return nextMessage;
    }

    private static void resetIdsOfUnsentMessages() {
        if(!idsOfUnsentMessages.isEmpty()) idsOfUnsentMessages.clear();

        for (Message message : ConfigSettings.messages) {
            if(!message.Enabled || message.ID == 0) continue;
            idsOfUnsentMessages.add(message.ID);
        }
    }
}
