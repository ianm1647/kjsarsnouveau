package com.bobvarioa.kubejsarsnoveau;

import com.mojang.logging.LogUtils;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(KubeJSArsNouveau.MODID)
public class KubeJSArsNouveau {
    public static final String MODID = "kubejsarsnoveau";
    private static final Logger LOGGER = LogUtils.getLogger();

    public KubeJSArsNouveau() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

    }
}
