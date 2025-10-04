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

import java.util.List;

@Environment(value = EnvType.CLIENT)
public class GRToastHandler {
    private static final MinecraftClient client = MinecraftClient.getInstance();
    public static int showMessageDuration = 20 * 6; // 6 seconds
    private static final float fadeOutDuration = 6.0F; // 6 ticks ~= 0.3 seconds

    public static void renderCustomToast(DrawContext drawContext, Message currentMessage) {
        // show message
        if(GRTickManager.totalTickCounter < GRTickManager.ticksWhenStartedShowing + showMessageDuration - fadeOutDuration) {
            showCustomToast(drawContext, currentMessage, 1.0F);
        }

        // at the beginning of fade out, play fade out sound
        if(GRTickManager.totalTickCounter == GRTickManager.ticksWhenStartedShowing + showMessageDuration - fadeOutDuration) {
            client.getSoundManager().play(PositionedSoundInstance.master(SoundEvents.UI_TOAST_OUT, 1.0F, 1.0F));
        }

        // fade out message
        if(GRTickManager.totalTickCounter >= GRTickManager.ticksWhenStartedShowing + showMessageDuration - fadeOutDuration) {
            float fadeOutAmount = MathHelper.clamp((float) Math.abs(GRTickManager.totalTickCounter - GRTickManager.ticksWhenStartedShowing - showMessageDuration) / fadeOutDuration, 0.0F, 1.0F);
            showCustomToast(drawContext, currentMessage, fadeOutAmount * fadeOutAmount);
        }
    }

    private static void showCustomToast(DrawContext drawContext, Message message, float fadeOutAmount) {
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

        if(ConfigSettings.getDisplayStyle().equals("custom")) {
            fillWithCustomTexture(drawContext, ConfigSettings.CUSTOM_BG_TEXTURE, ConfigSettings.CUSTOM_BORDER_TEXTURE, ConfigSettings.CUSTOM_INCLUDE_ICON, w - textureWWithFadeOut - 30, 0, textureW + 30, textureH);
        } else {
            // default, light, dark
            drawContext.drawTexture(ConfigSettings.toastTexture, w - textureWWithFadeOut - 30, 0, 0, 0, textureW + 30, textureH, textureW + 30, textureH);
        }

        for(int t = 0; t < titleLines.size(); ++t) {
            drawContext.drawText(client.textRenderer, titleLines.get(t), textStartX, 7 + t * 12, -1, false);
        }

        for(int j = 0; j < descriptionLines.size(); ++j) {
            drawContext.drawText(client.textRenderer, descriptionLines.get(j), textStartX, 18 + (j * 12) + ((titleLines.size() - 1) * 12), -1, false);
        }
    }

    private static void fillWithCustomTexture(DrawContext drawContext, Identifier bgTexture, Identifier borderTexture, Boolean includeIcon, int x, int y, int w, int h) {
        // Repeat the texture to fill the specified area
        // x and y are starting coordinates, w and h are width and height to fill
        
        int textureSize = 16; // Assuming the texture is 16x16 pixels
        
        // Calculate how many full textures we need horizontally and vertically
        int fullTexturesX = w / textureSize;
        int fullTexturesY = h / textureSize;
        
        // Calculate remaining pixels after full textures
        int remainingX = w % textureSize;
        int remainingY = h % textureSize;
        
        // Draw full textures
        for (int i = 0; i < fullTexturesX; i++) {
            for (int j = 0; j < fullTexturesY; j++) {
                drawContext.drawTexture(bgTexture, 
                    x + i * textureSize, y + j * textureSize, 
                    0, 0, textureSize, textureSize, textureSize, textureSize);
            }
        }
        
        // Draw partial textures for remaining width
        if (remainingX > 0) {
            for (int j = 0; j < fullTexturesY; j++) {
                drawContext.drawTexture(bgTexture, 
                    x + fullTexturesX * textureSize, y + j * textureSize, 
                    0, 0, remainingX, textureSize, textureSize, textureSize);
            }
        }
        
        // Draw partial textures for remaining height
        if (remainingY > 0) {
            for (int i = 0; i < fullTexturesX; i++) {
                drawContext.drawTexture(bgTexture, 
                    x + i * textureSize, y + fullTexturesY * textureSize, 
                    0, 0, textureSize, remainingY, textureSize, textureSize);
            }
        }
        
        // Draw the corner piece if both remaining dimensions exist
        if (remainingX > 0 && remainingY > 0) {
            drawContext.drawTexture(bgTexture, 
                x + fullTexturesX * textureSize, y + fullTexturesY * textureSize, 
                0, 0, remainingX, remainingY, textureSize, textureSize);
        }
        
        // Draw a 3 pixel wide black border around the texture        
        // Draw the top border
        drawContext.drawTexture(borderTexture, x, y, 0, 0, w, 3, 16, 16);
        // Draw the bottom border
        drawContext.drawTexture(borderTexture, x, y + h - 3, 0, 0, w, 3, 16, 16);
        // Draw the left border
        drawContext.drawTexture(borderTexture, x, y, 0, 0, 3, h, 16, 16);
        // Draw the right border
        drawContext.drawTexture(borderTexture, x + w - 3, y, 0, 0, 3, h, 16, 16);

        if(includeIcon) {
            drawContext.drawTexture(Identifier.ofVanilla("textures/gui/sprites/world_list/warning_highlighted.png"), x + 8, y + 6, 7, 6, 6, 22, 32, 32);
        }
    }
}
