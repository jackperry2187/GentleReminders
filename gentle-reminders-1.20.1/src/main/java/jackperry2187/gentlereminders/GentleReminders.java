package jackperry2187.gentlereminders;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static jackperry2187.gentlereminders.config.InitializeConfig.generateConfigFile;

public class GentleReminders implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("Gentle Reminders");
	public static final String MOD_ID = "gentlereminders";

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing Gentle Reminders...");

		if(FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
			generateConfigFile();
			// registerCommands();
		} else {
			LOGGER.warn("Gentle Reminders is a client-side mod and will not be loaded on the server!");
		}

		LOGGER.info("Initialized Successfully!");
	}

	public static Identifier id(String path) {
		return Identifier.of(MOD_ID, path);
	}
}