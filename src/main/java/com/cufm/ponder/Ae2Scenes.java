package com.cufm.ponder;

import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.createmod.ponder.api.scene.SceneBuilder;
import net.createmod.ponder.api.scene.SceneBuildingUtil;
import net.createmod.ponder.api.scene.Selection;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

/** AE2(应用能源2) 思索场景 —— 手工精写 */
public class Ae2Scenes {

    public static void register(PonderSceneRegistrationHelper<ItemLike> helper, ResourceLocation tag) {
        ItemLike controller = CufmPonderPlugin.item("ae2:controller");
        if (controller != null) {
            helper.addStoryBoard(controller, "ae2_controller", Ae2Scenes::controller, tag);
        }
        ItemLike drive = CufmPonderPlugin.item("ae2:drive");
        if (drive != null) {
            helper.addStoryBoard(drive, "ae2_drive", Ae2Scenes::drive, tag);
        }
        ItemLike acceptor = CufmPonderPlugin.item("ae2:energy_acceptor");
        if (acceptor != null) {
            helper.addStoryBoard(acceptor, "ae2_energy", Ae2Scenes::energy, tag);
        }
        ItemLike charger = CufmPonderPlugin.item("ae2:charger");
        if (charger != null) {
            helper.addStoryBoard(charger, "ae2_charger", Ae2Scenes::charger, tag);
        }
        ItemLike inscriber = CufmPonderPlugin.item("ae2:inscriber");
        if (inscriber != null) {
            helper.addStoryBoard(inscriber, "ae2_inscriber", Ae2Scenes::inscriber, tag);
        }
        ItemLike patternProvider = CufmPonderPlugin.item("ae2:pattern_provider");
        if (patternProvider != null) {
            helper.addStoryBoard(patternProvider, "ae2_autocrafting", Ae2Scenes::autocrafting, tag);
        }
        ItemLike quantumRing = CufmPonderPlugin.item("ae2:quantum_ring");
        if (quantumRing != null) {
            helper.addStoryBoard(quantumRing, "ae2_quantum_ring", Ae2Scenes::quantumRing, tag);
        }
    }

    /** ME 控制器 */
    public static void controller(SceneBuilder scene, SceneBuildingUtil util) {
        scene.title("ae2_controller", "ME Controller");
        scene.setNextUpEnabled(false);
        scene.configureBasePlate(0, 0, 7);
        scene.showBasePlate();
        scene.idle(10);

        BlockPos center = util.grid().at(3, 1, 3);
        scene.world().showSection(util.select().position(center), Direction.DOWN);
        scene.idle(20);

        scene.overlay().showText(70)
            .text("The ME Controller is the core of an ME Network. Every device on the network draws channels from it.")
            .placeNearTarget().attachKeyFrame().pointAt(util.vector().centerOf(center));
        scene.idle(80);

        BlockState ctrl = CufmPonderPlugin.state("ae2:controller");
        Selection cube = util.select().fromTo(2, 1, 2, 4, 3, 4);
        scene.world().setBlocks(cube, ctrl, false);
        scene.idle(20);
        scene.overlay().showText(80)
            .text("Place controllers next to each other and they merge into a larger multiblock, up to 7x7x7.")
            .placeNearTarget().attachKeyFrame().pointAt(util.vector().centerOf(2, 1, 2));
        scene.idle(90);

        scene.rotateCameraY(45);
        scene.overlay().showText(80)
            .text("The bigger the multiblock, the more channels the network can provide.")
            .placeNearTarget().attachKeyFrame().pointAt(util.vector().centerOf(3, 3, 3));
        scene.idle(90);
    }

    /** ME 驱动器 */
    public static void drive(SceneBuilder scene, SceneBuildingUtil util) {
        scene.title("ae2_drive", "ME Drive");
        scene.setNextUpEnabled(false);
        scene.configureBasePlate(0, 0, 7);
        scene.showBasePlate();
        scene.idle(10);

        BlockPos drivePos = util.grid().at(3, 1, 3);
        scene.world().showSection(util.select().position(drivePos), Direction.DOWN);
        scene.idle(20);

        scene.overlay().showText(70)
            .text("Insert storage cells into an ME Drive and your network gains storage space.")
            .placeNearTarget().attachKeyFrame().pointAt(util.vector().centerOf(drivePos));
        scene.idle(80);

        // 演示: 一个 1k 存储元件掉进驱动器
        var cellItem = BuiltInRegistries.ITEM.get(ResourceLocation.parse("ae2:item_storage_cell_1k"));
        if (cellItem != null && cellItem != net.minecraft.world.item.Items.AIR) {
            Vec3 dropPos = util.vector().topOf(drivePos).add(0, 1.5, 0);
            scene.world().createItemEntity(dropPos, new Vec3(0, -0.25, 0), new ItemStack(cellItem));
            scene.idle(40);
            scene.overlay().showText(70)
                .text("A 1k storage cell finds its way into the drive.")
                .placeNearTarget().attachKeyFrame().pointAt(util.vector().topOf(drivePos));
            scene.idle(80);
        }

        scene.overlay().showText(70)
            .text("One drive holds 10 cells, and you may mix cells of different sizes.")
            .placeNearTarget().attachKeyFrame().pointAt(util.vector().topOf(drivePos));
        scene.idle(80);

        scene.world().setBlock(new BlockPos(4, 1, 3), CufmPonderPlugin.state("ae2:energy_cell"), false);
        scene.idle(10);
        scene.overlay().showText(80)
            .text("The front face holds the cells. Cables connect to any other side of the drive.")
            .placeNearTarget().attachKeyFrame().pointAt(util.vector().centerOf(4, 1, 3));
        scene.idle(90);
    }

    /** 能量接收器 */
    public static void energy(SceneBuilder scene, SceneBuildingUtil util) {
        scene.title("ae2_energy", "Energy Acceptor");
        scene.setNextUpEnabled(false);
        scene.configureBasePlate(0, 0, 7);
        scene.showBasePlate();
        scene.idle(10);

        BlockPos acceptor = util.grid().at(3, 1, 2);
        BlockPos cell = util.grid().at(3, 1, 4);
        scene.world().showSection(util.select().position(acceptor), Direction.DOWN);
        scene.idle(15);
        scene.world().showSection(util.select().position(cell), Direction.DOWN);
        scene.idle(20);

        scene.overlay().showText(70)
            .text("An ME Network needs energy to run.")
            .placeNearTarget().attachKeyFrame().pointAt(util.vector().centerOf(acceptor));
        scene.idle(80);

        scene.overlay().showText(80)
            .text("The Energy Acceptor turns outside energy (FE) into AE, and feeds it into the network.")
            .placeNearTarget().attachKeyFrame().pointAt(util.vector().centerOf(acceptor));
        scene.idle(90);

        // 给能量元件注入电量(方块实体 NBT)
        scene.world().modifyBlockEntityNBT(util.select().position(cell), BlockEntity.class,
            nbt -> nbt.putDouble("internalCurrentPower", 200000.0));
        scene.effects().indicateSuccess(cell);
        scene.overlay().showText(80)
            .text("Place an Energy Cell next to it to buffer surplus AE for later.")
            .placeNearTarget().attachKeyFrame().pointAt(util.vector().centerOf(cell));
        scene.idle(90);
    }

    /** 充能器 */
    public static void charger(SceneBuilder scene, SceneBuildingUtil util) {
        scene.title("ae2_charger", "Charger");
        scene.setNextUpEnabled(false);
        scene.configureBasePlate(0, 0, 7);
        scene.showBasePlate();
        scene.idle(10);

        BlockPos pos = util.grid().at(3, 1, 3);
        scene.world().showSection(util.select().position(pos), Direction.DOWN);
        scene.idle(20);

        scene.overlay().showText(70)
            .text("The Charger uses AE energy to charge items placed inside.")
            .placeNearTarget().attachKeyFrame().pointAt(util.vector().centerOf(pos));
        scene.idle(80);

        scene.overlay().showText(80)
            .text("Power it through the ME network, or crank it by hand.")
            .placeNearTarget().attachKeyFrame().pointAt(util.vector().topOf(pos));
        scene.idle(90);

        scene.effects().indicateSuccess(pos);
        scene.overlay().showText(70)
            .text("Cells and rechargeable tools all charge here.")
            .placeNearTarget().attachKeyFrame().pointAt(util.vector().centerOf(pos));
        scene.idle(80);
    }

    /** 压印器 */
    public static void inscriber(SceneBuilder scene, SceneBuildingUtil util) {
        scene.title("ae2_inscriber", "Inscriber");
        scene.setNextUpEnabled(false);
        scene.configureBasePlate(0, 0, 7);
        scene.showBasePlate();
        scene.idle(10);

        BlockPos pos = util.grid().at(3, 1, 3);
        scene.world().showSection(util.select().position(pos), Direction.DOWN);
        scene.idle(20);

        scene.overlay().showText(70)
            .text("The Inscriber crafts circuits and processors.")
            .placeNearTarget().attachKeyFrame().pointAt(util.vector().centerOf(pos));
        scene.idle(80);

        scene.overlay().showText(80)
            .text("Put the material in the middle and a press on top.")
            .placeNearTarget().attachKeyFrame().pointAt(util.vector().topOf(pos));
        scene.idle(90);

        scene.rotateCameraY(-30);
        scene.overlay().showText(80)
            .text("The four sides accept extra materials like silicon or redstone.")
            .placeNearTarget().attachKeyFrame().pointAt(util.vector().centerOf(4, 1, 3));
        scene.idle(90);
    }

    /** 自动合成三件套 */
    public static void autocrafting(SceneBuilder scene, SceneBuildingUtil util) {
        scene.title("ae2_autocrafting", "Autocrafting");
        scene.setNextUpEnabled(false);
        scene.configureBasePlate(0, 0, 7);
        scene.showBasePlate();
        scene.idle(10);

        BlockPos provider = util.grid().at(2, 1, 2);
        BlockPos assembler = util.grid().at(2, 1, 3);
        BlockPos iface = util.grid().at(2, 1, 4);
        scene.world().showSection(util.select().position(assembler), Direction.DOWN);
        scene.idle(15);
        scene.world().showSection(util.select().position(provider), Direction.WEST);
        scene.idle(15);
        scene.world().showSection(util.select().position(iface), Direction.EAST);
        scene.idle(20);

        scene.overlay().showText(80)
            .text("The autocrafting trio: Pattern Provider, Molecular Assembler and ME Interface.")
            .placeNearTarget().attachKeyFrame().pointAt(util.vector().centerOf(assembler));
        scene.idle(90);

        scene.overlay().showText(90)
            .text("Encode a pattern into the Interface, and the Pattern Provider hands the job to the Assembler.")
            .placeNearTarget().attachKeyFrame().pointAt(util.vector().centerOf(provider));
        scene.idle(100);

        scene.effects().indicateSuccess(assembler);
        scene.overlay().showText(80)
            .text("When the network requests a craft, this setup builds it automatically.")
            .placeNearTarget().attachKeyFrame().pointAt(util.vector().centerOf(iface));
        scene.idle(90);
    }

    /** 量子环 */
    public static void quantumRing(SceneBuilder scene, SceneBuildingUtil util) {
        scene.title("ae2_quantum_ring", "Quantum Ring");
        scene.setNextUpEnabled(false);
        scene.configureBasePlate(0, 0, 7);
        scene.showBasePlate();
        scene.idle(10);

        Selection ring = util.select().fromTo(2, 1, 2, 4, 1, 4).substract(util.select().position(3, 1, 3));
        scene.world().showSection(ring, Direction.DOWN);
        scene.idle(20);
        scene.world().showSection(util.select().position(3, 1, 3), Direction.UP);
        scene.idle(20);

        scene.overlay().showText(80)
            .text("A Quantum Ring is 8 Quantum Ring blocks in a circle with a Quantum Link in the middle.")
            .placeNearTarget().attachKeyFrame().pointAt(util.vector().centerOf(3, 1, 2));
        scene.idle(90);

        scene.rotateCameraY(60);
        scene.overlay().showText(80)
            .text("Build one ring at each of two locations and they will pair up.")
            .placeNearTarget().attachKeyFrame().pointAt(util.vector().centerOf(3, 1, 3));
        scene.idle(90);

        scene.effects().indicateSuccess(util.grid().at(3, 1, 3));
        scene.overlay().showText(90)
            .text("Once paired, the two networks link wirelessly across dimensions - like a very long cable.")
            .placeNearTarget().attachKeyFrame().pointAt(util.vector().topOf(3, 1, 3));
        scene.idle(100);
    }
}
