package com.envyful.pixelmon.overlay.forge.config.type;

import com.envyful.api.forge.items.ItemBuilder;
import com.envyful.pixelmon.overlay.forge.config.ConfigData;
import com.pixelmonmod.pixelmon.api.util.helpers.ResourceLocationHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;

@ConfigSerializable
public class ItemConfigData implements ConfigData<ItemStack> {

    private String type;

    public ItemConfigData() {
    }

    public ItemConfigData(String type) {
        this.type = type;
    }

    @Override
    public ItemStack build() {
        return new ItemBuilder()
                .type(ForgeRegistries.ITEMS.getValue(ResourceLocationHelper.of(this.type)))
                .build();
    }
}
