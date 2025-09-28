package jackperry2187.gentlereminders;

import jackperry2187.gentlereminders.config.client.ConfigSettings;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

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

		// TODO: Deprecated impementation, looks like it should be updated to use the new HudElementRegistry
		HudRenderCallback.EVENT.register(((drawContext, renderTickCounter) -> {
			// EnvType == EnvType.CLIENT
			if(!ConfigSettings.enabled) return;

			// send over the draw context to the HUD handler
			HUDHandler(drawContext);
		}));

		ClientTickEvents.END_WORLD_TICK.register(world -> {
			// EnvType == EnvType.CLIENT
			if(!ConfigSettings.enabled) return;

			tickHandler();
		});
	}
}