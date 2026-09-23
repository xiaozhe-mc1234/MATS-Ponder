package com.cufm.ponder;

import net.createmod.ponder.foundation.PonderIndex;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLLoader;

/**
 * CUFM 思索扩展 —— 给 AE2(应用能源2) 和 Supplementaries(锦致装饰) 的方块加思索教程。
 *
 * 注册方式与 Create 本体一致: 客户端初始化时把自己的 PonderPlugin 塞进 PonderIndex。
 * 方块全部用 ResourceLocation 动态查找, 不依赖这两个模组的编译期 API,
 * 模组不在时自动跳过对应场景。
 */
@Mod("cufmponder")
public class PonderMod {
    public PonderMod() {
        if (FMLLoader.getDist().isClient()) {
            PonderIndex.addPlugin(new CufmPonderPlugin());
        }
    }
}
