package jackperry2187.gentlereminders;

import jackperry2187.gentlereminders.config.client.ConfigSettings;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;

import static jackperry2187.gentlereminders.commands.client.RegisterCommands.*;
import static jackperry2187.gentlereminders.handler.client.GRHUDHandler.HUDHandler;
import static jackperry2187.gentlereminders.handler.client.GRTickManager.tickHandler;

public class GentleRemindersClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// initialize the config settings
		ConfigSettings.initialize();

		// initialize client arguments and commands
		registerArguments();
		registerClientCommands();

		HudElementRegistry.attachElementBefore(VanillaHudElements.CHAT, GentleReminders.id("gr_hud"), GentleRemindersClient::render);

		ClientTickEvents.END_WORLD_TICK.register(world -> {
			// EnvType == EnvType.CLIENT
			if(!ConfigSettings.enabled) return;

			tickHandler();
		});
	}

	private static void render(DrawContext drawContext, RenderTickCounter renderTickCounter) {
		// EnvType == EnvType.CLIENT
		if(!ConfigSettings.enabled) return;

		// send over the draw context to the HUD handler
		HUDHandler(drawContext);
	}
}