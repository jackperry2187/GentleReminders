package jackperry2187.gentlereminders.util;

import jackperry2187.gentlereminders.GentleReminders;
import jackperry2187.gentlereminders.config.ConfigSettings;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.toast.SystemToast;

import java.util.ArrayList;
import java.util.List;

@Environment(value = EnvType.CLIENT)
public class SendClientMessage {
    private static int lastMessageIndex = -1;
    private static int numberOfMessagesSent = 0;
    private static final List<Integer> idsOfUnsentMessages = new ArrayList<>();

    public static void sendInitialMessage(MinecraftClient client) {
        if(lastMessageIndex != -1) {
            GentleReminders.LOGGER.error("Initial message has already been sent!");
            return;
        }
        Message intialMessage = ConfigSettings.messages.get(0);

        client.getToastManager().add(SystemToast.create(
                client,
                SystemToast.Type.PERIODIC_NOTIFICATION,
                intialMessage.Title,
                intialMessage.Description
        ));

        lastMessageIndex = 0;
    }

    public static void sendPeriodicMessage(MinecraftClient client) {
        if(lastMessageIndex == -1) {
            GentleReminders.LOGGER.error("Initial message has not been sent!");
            return;
        }

        if(numberOfMessagesSent == ConfigSettings.messages.size() - 1) {
            numberOfMessagesSent = 0;
            idsOfUnsentMessages.clear();
        }

        if(idsOfUnsentMessages.isEmpty()) resetIdsOfUnsentMessages();

        // generate a random index to get the next message
        int nextMessageIndex = (int) (Math.random() * (idsOfUnsentMessages.size() - 1));
        Message nextMessage = ConfigSettings.messages.get(idsOfUnsentMessages.get(nextMessageIndex));

        client.getToastManager().add(SystemToast.create(
                client,
                SystemToast.Type.PERIODIC_NOTIFICATION,
                nextMessage.Title,
                nextMessage.Description
        ));

        numberOfMessagesSent++;
        idsOfUnsentMessages.remove(nextMessageIndex);
        lastMessageIndex = nextMessageIndex;
    }

    private static void resetIdsOfUnsentMessages() {
        if(!idsOfUnsentMessages.isEmpty()) idsOfUnsentMessages.clear();

        for (Message message : ConfigSettings.messages) {
            if(!message.Enabled) continue;
            idsOfUnsentMessages.add(message.ID);
        }
    }
}
