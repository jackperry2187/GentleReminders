package jackperry2187.gentlereminders.handler.client;

import jackperry2187.gentlereminders.config.client.ConfigSettings;
import jackperry2187.gentlereminders.util.Message;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.OrderedText;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import net.minecraft.client.gl.RenderPipelines;

import java.util.List;

@Environment(value = EnvType.CLIENT)
public class GRToastHandler {
    private static final MinecraftClient client = MinecraftClient.getInstance();
    public static int showMessageDuration = 20 * 6; // 6 seconds
    private static final float fadeOutDuration = 6.0F; // 6 ticks ~= 0.3 seconds

    public static void renderCustomToast(DrawContext drawContext, Message currentMessage) {
        // show message
        if(GRTickManager.totalTickCounter < GRTickManager.ticksWhenStartedShowing + showMessageDuration - fadeOutDuration) {
            showCustomToast(drawContext, currentMessage, 1.0F, ConfigSettings.toastTexture);
        }

        // at the beginning of fade out, play fade out sound
        if(GRTickManager.totalTickCounter == GRTickManager.ticksWhenStartedShowing + showMessageDuration - fadeOutDuration) {
            client.getSoundManager().play(PositionedSoundInstance.master(SoundEvents.UI_TOAST_OUT, 1.0F, 1.0F));
        }

        // fade out message
        if(GRTickManager.totalTickCounter >= GRTickManager.ticksWhenStartedShowing + showMessageDuration - fadeOutDuration) {
            float fadeOutAmount = MathHelper.clamp((float) Math.abs(GRTickManager.totalTickCounter - GRTickManager.ticksWhenStartedShowing - showMessageDuration) / fadeOutDuration, 0.0F, 1.0F);
            showCustomToast(drawContext, currentMessage, fadeOutAmount * fadeOutAmount, ConfigSettings.toastTexture);
        }
    }

    private static void showCustomToast(DrawContext drawContext, Message message, float fadeOutAmount, Identifier texture) {
        int w = drawContext.getScaledWindowWidth();
        TextRenderer textRenderer = client.textRenderer;

        List<OrderedText> titleLines = textRenderer.wrapLines(message.Title, 200);
        List<OrderedText> descriptionLines = textRenderer.wrapLines(message.Description, 200);

        int textureW;
        if(titleLines.size() == 1 && descriptionLines.size() == 1 && textRenderer.getWidth(message.Title) <= 180 && textRenderer.getWidth(message.Description) <= 180) {
            textureW = Math.max(textRenderer.getWidth(message.Title), textRenderer.getWidth(message.Description)) + 20;
        } else {
            textureW = Math.max(200, titleLines.stream().mapToInt(textRenderer::getWidth).max().orElse(200));
        }
        int textureWWithFadeOut = MathHelper.ceil(textureW * fadeOutAmount);
        int textureH = 20 + ((titleLines.size() - 1) * 12) + descriptionLines.size() * 12;

        int textStartX = w - textureWWithFadeOut - 5;
        
        drawContext.drawTexture(RenderPipelines.GUI_TEXTURED, texture, w - textureWWithFadeOut - 30, 0, 0, 0, textureW + 30, textureH, textureW + 30, textureH);

        for(int t = 0; t < titleLines.size(); ++t) {
            drawContext.drawText(client.textRenderer, titleLines.get(t), textStartX, 7 + t * 12, -1, false);
        }

        for(int j = 0; j < descriptionLines.size(); ++j) {
            drawContext.drawText(client.textRenderer, descriptionLines.get(j), textStartX, 18 + (j * 12) + ((titleLines.size() - 1) * 12), -1, false);
        }
    }
}
