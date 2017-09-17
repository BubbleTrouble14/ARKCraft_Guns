package com.bubble.gunmod.crafting;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import com.bubble.gunmod.Main;
import com.bubble.gunmod.crafting.ContainerWeaponCrafting.WeaponRecipe;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiButtonImage;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class GuiWeaponCrafting extends GuiContainer {

	public GuiWeaponCrafting(ContainerWeaponCrafting container) {
		super(container);
	}

	@Override
	public void initGui() {
		super.initGui();
		// addButton(new GuiButtonExt(0, 200, 200, "left"));
		// addButton(new GuiButtonExt(1, 250, 200, "right"));
		ResourceLocation loc = new ResourceLocation(Main.MODID, "textures/gui/crafting_buttons.png");
		addButton(new GuiButtonImage(0, this.guiLeft + 79 - 23 - 10, this.guiTop + 30, 22, 15, 0, 15, 0, loc));
		addButton(new GuiButtonImage(1, this.guiLeft + 79 + 17 + 10, this.guiTop + 30, 22, 15, 0, 0, 0, loc));
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
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		// TODO Auto-generated method stub
		super.mouseClicked(mouseX, mouseY, mouseButton);
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		renderHoveredToolTip(mouseX, mouseY);
	}

	@Override
	protected void renderToolTip(ItemStack stack, int x, int y) {
		x = x - this.guiLeft;
		y = y - this.guiTop;
		System.out.println("called");
		if (x >= ((ContainerWeaponCrafting) inventorySlots).getXPOS()-1
				&& x < ((ContainerWeaponCrafting) inventorySlots).getXPOS() + 17
				&& y >= ((ContainerWeaponCrafting) inventorySlots).getYPOS()-1
				&& y < ((ContainerWeaponCrafting) inventorySlots).getYPOS() + 17) {
			FontRenderer font = stack.getItem().getFontRenderer(stack);
			net.minecraftforge.fml.client.config.GuiUtils.preItemToolTip(stack);
			List<String> tooltip = new ArrayList<>();
			WeaponRecipe recipe = ContainerWeaponCrafting.getRecipes()
					.get(((ContainerWeaponCrafting) this.inventorySlots).getIndex());
			tooltip.add(recipe.getOutput().getDisplayName());
			for (ItemStack in : recipe.getInputs())
				tooltip.add(in.getDisplayName() + " x " + in.getCount());
			this.drawHoveringText(tooltip, x + this.guiLeft, y + this.guiTop, (font == null ? fontRenderer : font));
			net.minecraftforge.fml.client.config.GuiUtils.postItemToolTip();
		} else
			super.renderToolTip(stack, x + this.guiLeft, y + this.guiTop);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(new ResourceLocation(Main.MODID, "textures/gui/attachment_gui.png"));
		int k = (width - xSize) / 2;
		int l = (height - ySize) / 2;
		drawTexturedModalRect(k, l, 0, 0, xSize, ySize);
	}
}
