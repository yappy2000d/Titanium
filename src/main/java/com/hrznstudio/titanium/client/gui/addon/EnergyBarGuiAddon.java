/*
 * This file is part of Titanium
 * Copyright (C) 2018, Horizon Studio <contact@hrznstudio.com>, All rights reserved.
 */
package com.hrznstudio.titanium.client.gui.addon;

import com.hrznstudio.titanium.api.client.IAsset;
import com.hrznstudio.titanium.client.gui.GuiContainerTile;
import com.hrznstudio.titanium.client.gui.asset.IAssetProvider;
import net.minecraftforge.energy.IEnergyStorage;

import java.awt.*;

public class EnergyBarGuiAddon extends BasicGuiAddon {

    private final IEnergyStorage handler;

    public EnergyBarGuiAddon(int posX, int posY, IEnergyStorage handler) {
        super(posX, posY);
        this.handler = handler;
    }

    @Override
    public void drawGuiContainerBackgroundLayer(GuiContainerTile container, float partialTicks, int mouseX, int mouseY) {
        IAsset asset = IAssetProvider.getAsset(container.getAssetProvider(), IAssetProvider.AssetType.ENERGY_BAR);
        Point offset = asset.getOffset();
        Rectangle area = asset.getArea();
        container.mc.getTextureManager().bindTexture(asset.getResourceLocation());
        container.drawTexturedModalRect(container.getX() + getPosX() + offset.x, container.getY() + getPosY() + offset.y, area.x, area.y, area.width, area.height);
    }

    @Override
    public void drawGuiContainerForegroundLayer(GuiContainerTile container, int mouseX, int mouseY) {
        IAsset asset = IAssetProvider.getAsset(container.getAssetProvider(), IAssetProvider.AssetType.ENERGY_FILL);
        Point offset = asset.getOffset();
        Rectangle area = asset.getArea();
        container.mc.getTextureManager().bindTexture(asset.getResourceLocation());
        int stored = handler.getEnergyStored();
        int capacity = handler.getMaxEnergyStored();
        int powerOffset = stored * area.height / capacity;
        handler.receiveEnergy(10, false);
        container.drawTexturedModalRect(getPosX() + offset.x, getPosY() + offset.y + area.height - powerOffset, area.x, area.y + (area.height - powerOffset), area.width, powerOffset);
    }

    @Override
    public int getXSize() {
        return 0;
    }

    @Override
    public int getYSize() {
        return 0;
    }
}