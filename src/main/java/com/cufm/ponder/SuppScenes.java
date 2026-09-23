package com.cufm.ponder;

import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.createmod.ponder.api.scene.SceneBuilder;
import net.createmod.ponder.api.scene.SceneBuildingUtil;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.ItemLike;

/** Supplementaries(锦致装饰) 思索场景 */
public class SuppScenes {

    public static void register(PonderSceneRegistrationHelper<ItemLike> helper, ResourceLocation tag) {
        ItemLike launcher = CufmPonderPlugin.item("supplementaries:spring_launcher");
        if (launcher != null) {
            helper.addStoryBoard(launcher, "supp_spring_launcher", SuppScenes::springLauncher, tag);
        }
        ItemLike pulley = CufmPonderPlugin.item("supplementaries:pulley_block");
        if (pulley != null) {
            helper.addStoryBoard(pulley, "supp_rope_pulley", SuppScenes::ropePulley, tag);
        }
    }

    /** 弹簧发射器 */
    public static void springLauncher(SceneBuilder scene, SceneBuildingUtil util) {
        scene.title("supp_spring_launcher", "Spring Launcher");
        scene.setNextUpEnabled(false);
        scene.configureBasePlate(0, 0, 7);
        scene.showBasePlate();
        scene.idle(10);

        BlockPos pos = util.grid().at(3, 1, 3);
        scene.world().showSection(util.select().position(pos), Direction.DOWN);
        scene.idle(20);

        scene.overlay().showText(70)
            .text("Fall onto a Spring Launcher from a height and it throws you right back up.")
            .placeNearTarget().attachKeyFrame().pointAt(util.vector().centerOf(pos));
        scene.idle(80);

        scene.overlay().showText(80)
            .text("The higher you fall, the stronger the launch. Trigger height and power are configurable.")
            .placeNearTarget().attachKeyFrame().pointAt(util.vector().topOf(pos));
        scene.idle(90);

        scene.effects().indicateSuccess(pos);
        scene.overlay().showText(70)
            .text("Build one at your base entrance for a bouncy way in.")
            .placeNearTarget().attachKeyFrame().pointAt(util.vector().centerOf(pos));
        scene.idle(80);
    }

    /** 绳索与滑轮块 */
    public static void ropePulley(SceneBuilder scene, SceneBuildingUtil util) {
        scene.title("supp_rope_pulley", "Rope & Pulley Block");
        scene.setNextUpEnabled(false);
        scene.configureBasePlate(0, 0, 7);
        scene.showBasePlate();
        scene.idle(10);

        BlockPos pulley = util.grid().at(3, 3, 3);
        BlockPos ropeTop = util.grid().at(3, 2, 3);
        BlockPos ropeBottom = util.grid().at(3, 1, 3);

        scene.world().showSection(util.select().position(pulley), Direction.DOWN);
        scene.idle(10);
        scene.world().showSection(util.select().fromTo(3, 1, 3, 3, 2, 3), Direction.UP);
        scene.idle(20);

        scene.overlay().showText(70)
            .text("Ropes can be climbed. Walk into one to move up or down.")
            .placeNearTarget().attachKeyFrame().pointAt(util.vector().centerOf(ropeBottom));
        scene.idle(80);

        scene.overlay().showText(80)
            .text("A Pulley Block extends and retracts its rope on its own.")
            .placeNearTarget().attachKeyFrame().pointAt(util.vector().centerOf(pulley));
        scene.idle(90);

        scene.rotateCameraY(-30);
        scene.overlay().showText(90)
            .text("Several pulleys can work together to lift a very heavy build - great for elevators.")
            .placeNearTarget().attachKeyFrame().pointAt(util.vector().centerOf(ropeTop));
        scene.idle(90);
    }
}
