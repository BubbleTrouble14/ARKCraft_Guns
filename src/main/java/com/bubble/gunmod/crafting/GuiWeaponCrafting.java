package com.bubble.gunmod.crafting;

import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraftforge.fml.client.config.GuiButtonExt;

public class GuiWeaponCrafting extends GuiContainer {

    public GuiWeaponCrafting(ContainerWeaponCrafting container) {
	super(container);
    }

    @Override
    public void initGui() {
	super.initGui();
	addButton(new GuiButtonExt(0, 200, 200, "left"));
	addButton(new GuiButtonExt(1, 250, 200, "right"));
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
	if (button.id == 0) {
	    ((ContainerWeaponCrafting) inventorySlots).moveLeft();
	    mc.playerController.sendEnchantPacket(inventorySlots.windowId, 0);
	} else if (button.id == 1) {
	    ((ContainerWeaponCrafting) inventorySlots).moveRight();
	    mc.playerController.sendEnchantPacket(inventorySlots.windowId, 1);
	} else
	    super.actionPerformed(button);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
	// TODO Auto-generated method stub
    }
}
