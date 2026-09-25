package com.mats.ponder;
import net.createmod.ponder.api.registration.PonderPlugin;
import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.createmod.ponder.api.registration.PonderTagRegistrationHelper;
import net.createmod.ponder.api.registration.TagBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
public class MatsPonderPlugin implements PonderPlugin {
    private static final String TAG_AE2 = "ae2";
    private static final String TAG_SUPP = "supplementaries";
    private static final String TAG_AMEND = "amendments";
    @Override
    public String getModId() {
        return "matsponder";
    }
    @Override
    public void registerScenes(PonderSceneRegistrationHelper<ResourceLocation> helper) {
        PonderSceneRegistrationHelper<ItemLike> h =
            helper.withKeyFunction(item -> BuiltInRegistries.ITEM.getKey(item.asItem()));
        Ae2Scenes.register(h, helper.asLocation(TAG_AE2));
        SuppScenes.register(h, helper.asLocation(TAG_SUPP));
        AutoScenes.register(h, helper.asLocation(TAG_AE2), helper.asLocation(TAG_SUPP), helper.asLocation(TAG_AMEND));
    }
    @Override
    public void registerTags(PonderTagRegistrationHelper<ResourceLocation> helper) {
        tag(helper, TAG_AE2, "应用能源2", "应用能源2 的全部可思索方块: 控制器、线缆、存储、自动合成、石英与陨石建材", "ae2:controller");
        tag(helper, TAG_SUPP, "锦致装饰", "锦致装饰的全部可思索方块: 礼物盒、旗帜、彩旗、遮阳棚、烛台、绳索、弹射台等等", "supplementaries:globe");
        tag(helper, TAG_AMEND, "Amendments", "锦致装饰附属 Amendments 的方块: 吊顶旗帜、头颅烛台、悬挂花盆、墙上的灯笼", "amendments:skull_pile");
    }
    private static void tag(PonderTagRegistrationHelper<ResourceLocation> helper, String id,
                            String title, String description, String iconId) {
        TagBuilder b = helper.registerTag(id).title(title).description(description).addToIndex();
        ItemLike icon = item(iconId);
        if (icon != null) {
            b.item(icon, true, true);
        }
        b.register();
    }
    public static ItemLike item(String id) {
        Item item = BuiltInRegistries.ITEM.get(ResourceLocation.parse(id));
        if (item == null || item == net.minecraft.world.item.Items.AIR) {
            return null;
        }
        return item;
    }
    public static boolean exists(String id) {
        return item(id) != null;
    }
    public static net.minecraft.world.level.block.state.BlockState state(String id) {
        net.minecraft.world.level.block.Block b =
            BuiltInRegistries.BLOCK.get(ResourceLocation.parse(id));
        if (b == null || b == net.minecraft.world.level.block.Blocks.AIR) {
            return net.minecraft.world.level.block.Blocks.STONE.defaultBlockState();
        }
        return b.defaultBlockState();
    }
}