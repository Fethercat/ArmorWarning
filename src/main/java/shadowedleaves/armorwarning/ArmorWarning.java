package shadowedleaves.armorwarning;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArmorWarning implements ModInitializer {
	public static final String MOD_ID = "armorwarning";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("[ArmorWarning] ArmorWarning has been initialized!");
	}
}