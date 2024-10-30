package jackperry2187.gentlereminders;

import jackperry2187.gentlereminders.config.ConfigSettings;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;

import static jackperry2187.gentlereminders.clientCommands.RegisterCommands.*;
import static jackperry2187.gentlereminders.util.SendClientMessage.*;

public class GentleRemindersClient implements ClientModInitializer {
	public static int tickCounter = 0;
	private static final MinecraftClient client = MinecraftClient.getInstance();
	@Override
	public void onInitializeClient() {
		// initialize the config settings
		ConfigSettings.initialize();

		// initialize client arguments and commands
		registerArguments();
		registerClientCommands();

		ClientTickEvents.END_WORLD_TICK.register(world -> {
			// EnvType == EnvType.CLIENT
			if(!ConfigSettings.enabled) return;

			// triggers as soon as a world is loaded
			if(tickCounter == 0) sendInitialMessage(client);

			// every X seconds send a mindful message
			if(tickCounter != 0 && tickCounter % ConfigSettings.ticksBetweenMessages == 0) sendPeriodicMessage(client);

			// once in a world and the mod is enabled, increment the tick counter
			tickCounter++;
		});
	}
}