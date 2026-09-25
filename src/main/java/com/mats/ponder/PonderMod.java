package com.mats.ponder;
import net.createmod.ponder.foundation.PonderIndex;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLLoader;
@Mod("matsponder")
public class PonderMod {
    public PonderMod() {
        if (FMLLoader.getDist().isClient()) {
            PonderIndex.addPlugin(new MatsPonderPlugin());
        }
    }
}