package dev.hail.hail_addon;

import com.mrcrayfish.framework.api.registry.RegistryContainer;
import com.mrcrayfish.framework.api.registry.RegistryEntry;
import dev.hail.hail_addon.Item.RotItem;
import net.minecraft.world.item.Item;
@RegistryContainer
public class HAItem {
    public static final RegistryEntry<Item> ROT_WRENCH = RegistryEntry.item(HAUtils.resource("rot_wrench"), () -> {
        return new RotItem((new Item.Properties()).stacksTo(1),1);
    });
}
