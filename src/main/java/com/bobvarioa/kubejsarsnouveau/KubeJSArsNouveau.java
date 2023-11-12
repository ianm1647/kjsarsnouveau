package com.bobvarioa.kubejsarsnouveau;

import com.mojang.logging.LogUtils;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(KubeJSArsNouveau.MODID)
public class KubeJSArsNouveau {
    public static final String MODID = "kubejsarsnouveau";
    private static final Logger LOGGER = LogUtils.getLogger();

    public KubeJSArsNouveau() {
        var modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

    }
}
