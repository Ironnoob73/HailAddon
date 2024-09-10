package dev.hail.hail_addon.registries;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.yuushya.registries.YuushyaRegistryConfig;
import com.yuushya.registries.YuushyaRegistryData;
import com.yuushya.utils.GsonTools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static com.mojang.text2speech.Narrator.LOGGER;

public class HailAddonRegistryConfig extends YuushyaRegistryConfig {
    private static YuushyaRegistryData HailAddonData;
    public static final InputStream InnerFileInputStream = HailAddonRegistryConfig.class.getResourceAsStream("/data/yuushya/register/inner.json");
    public static void readRegistrySelf() {
        readRegistryInner();
        addResultToRawMap(HailAddonData);
    }
    public static void readRegistryInner() {
        if (InnerFileInputStream != null) {
            LOGGER.info("REGISTRY HAIL ADDON BLOCK");
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(InnerFileInputStream));

                try {
                    LOGGER.info("REGISTRY IS RUNNING");
                    JsonElement innerJson = JsonParser.parseReader(reader);
                    mergeYuushyaRegistryBlockJson(innerJson.getAsJsonObject().getAsJsonArray("block"));
                    HailAddonData = GsonTools.NormalGSON.fromJson(innerJson, YuushyaRegistryData.class);
                } catch (Throwable var4) {
                    try {
                        reader.close();
                    } catch (Throwable var3) {
                        var4.addSuppressed(var3);
                    }

                    throw var4;
                }

                reader.close();
            } catch (IOException var5) {
                var5.printStackTrace();
            }
        }
        else {
            LOGGER.info("NO INFO");
        }

    }
}
