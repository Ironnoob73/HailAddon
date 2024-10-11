package dev.hail.hail_addon;

import com.mrcrayfish.furniture.refurbished.util.Utils;
import net.minecraft.resources.ResourceLocation;

public class HAUtils extends Utils {
    public static ResourceLocation resource(String name) {
        return ResourceLocation.fromNamespaceAndPath("hail_addon", name);
    }
}
