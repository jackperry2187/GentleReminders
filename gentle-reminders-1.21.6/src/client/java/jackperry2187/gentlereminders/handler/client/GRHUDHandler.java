package jackperry2187.gentlereminders.handler.client;

import jackperry2187.gentlereminders.config.client.ConfigSettings;
import jackperry2187.gentlereminders.util.Message;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.toast.SystemToast;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Text;
import net.minecraft.util.Colors;

import java.util.List;

@Environment(value = EnvType.CLIENT)
public class GRHUDHandler {
    private static final MinecraftClient client = MinecraftClient.getInstance();

    private static boolean isShowingCustomMessage = false;
    private static Message currentMessage = null;
    private static final boolean isDebug = false;

    public static void HUDHandler(DrawContext drawContext) {
        if(isShowingCustomMessage) {
            GRToastHandler.renderCustomToast(drawContext, currentMessage);
        }

        if(isDebug) {
            drawDebug(drawContext, currentMessage);
        }
    }

    private static void addDefaultToast(Message message) {
        client.getToastManager().add(SystemToast.create(
                client,
                SystemToast.Type.PERIODIC_NOTIFICATION,
                message.Title,
                message.Description
        ));
    }

    private static void addChatMessage(Message message) {
        Text combinedText = ScreenTexts.joinLines(message.Title, message.Description);
        client.player.sendMessage(combinedText, false);
    }

    public static void startShowingMessage() {
        switch(ConfigSettings.getDisplayStyle()) {
            case "default":
                // the default toast already plays a sound so we don't need to
                currentMessage = currentMessage != null ? currentMessage : GRMessageHandler.getRandomMessage();
                addDefaultToast(currentMessage);
                break;
            case "light":
            case "dark":
            case "custom":
                client.getSoundManager().play(PositionedSoundInstance.master(SoundEvents.UI_TOAST_IN, 1.0F, 1.0F));
                currentMessage = currentMessage != null ? currentMessage : GRMessageHandler.getRandomMessage();
                isShowingCustomMessage = true;
                break;
            case "chat":
                client.getSoundManager().play(PositionedSoundInstance.master(SoundEvents.BLOCK_DECORATED_POT_INSERT, 1.0F, 1.0F));
                currentMessage = currentMessage != null ? currentMessage : GRMessageHandler.getRandomMessage();
                addChatMessage(currentMessage);
                break;
            default:
                break;
        }
    }

    public static void stopShowingMessage() {
        isShowingCustomMessage = false;
        currentMessage = null;
    }

    private static void drawDebug(DrawContext drawContext, Message message) {
        int w = drawContext.getScaledWindowWidth();
        int h = drawContext.getScaledWindowHeight();

        TextRenderer textRenderer = client.textRenderer;
        List<OrderedText> descriptionLines = textRenderer.wrapLines(message.Description, 200);
        int textureW = Math.max(200, descriptionLines.stream().mapToInt(textRenderer::getWidth).max().orElse(200));

        drawContext.drawHorizontalLine(0, w, h / 2, Colors.RED);
        drawContext.drawVerticalLine(w / 2, 0, h, Colors.RED);

        drawContext.drawVerticalLine(w - 160, 0, h, Colors.GREEN);
        drawContext.drawHorizontalLine(0, w, 7, Colors.RED);
        drawContext.drawHorizontalLine(0, w, 20, Colors.BLUE);
        drawContext.drawVerticalLine(w - textureW - 30, 0, h, Colors.BLUE);
        drawContext.drawVerticalLine(w - textureW - 18, 0, h, Colors.YELLOW);
        drawContext.drawVerticalLine(w - textureW, 0, h, Colors.WHITE);
    }
}
