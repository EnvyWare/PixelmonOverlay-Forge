package com.envyful.pixelmon.overlay.forge.config.type;

import com.envyful.api.forge.items.ItemBuilder;
import com.envyful.pixelmon.overlay.forge.config.ConfigData;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.spongepowered.configurate.objectmapping.ConfigSerializable;

@ConfigSerializable
public class ItemConfigData implements ConfigData<ItemStack> {

    private String type;
    private int damage;

    public ItemConfigData() {
    }

    public ItemConfigData(String type, int damage) {
        this.type = type;
        this.damage = damage;
    }

    @Override
    public ItemStack build() {
        return new ItemBuilder()
                .type(Item.getByNameOrId(this.type))
                .damage(this.damage)
                .build();
    }
}
