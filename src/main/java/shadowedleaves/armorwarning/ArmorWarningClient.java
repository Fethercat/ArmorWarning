package shadowedleaves.armorwarning;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class ArmorWarningClient implements ClientModInitializer {
    private boolean[] wasWearingArmor = new boolean[4];

    @Override
    public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.world != null && client.player != null) {
                checkArmor(client.player);
            }
        });
    }

    private void checkArmor(ClientPlayerEntity player) {
        EquipmentSlot[] armorSlots = {
                EquipmentSlot.HEAD,
                EquipmentSlot.CHEST,
                EquipmentSlot.LEGS,
                EquipmentSlot.FEET
        };

        for (int i = 0; i < armorSlots.length; i++) {
            ItemStack stack = player.getEquippedStack(armorSlots[i]);
            if (stack.isEmpty() && wasWearingArmor[i]) {
                String armorPieceName = armorSlots[i].getName().toUpperCase();
                sendWarning(player, armorPieceName);
                wasWearingArmor[i] = false;
            } else if (!stack.isEmpty()) {
                wasWearingArmor[i] = true;
            }
        }
    }

    private void sendWarning(ClientPlayerEntity player, String armorPieceName) {
        Text warningMessage = Text.literal("PLEASE RE-EQUIP YOUR " + armorPieceName + "!!")
                .formatted(Formatting.BOLD, Formatting.DARK_RED);
        for (int i = 0; i < 15; i++) {
            player.sendMessage(warningMessage, false);
        }
    }
}