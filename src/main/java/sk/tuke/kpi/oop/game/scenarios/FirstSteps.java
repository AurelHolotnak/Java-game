/*package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.GameApplication;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.SceneListener;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.When;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.oop.game.DefectiveLight;
import sk.tuke.kpi.oop.game.Reactor;
import sk.tuke.kpi.oop.game.actions.Move;
import sk.tuke.kpi.oop.game.actions.Use;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.controllers.KeeperController;
import sk.tuke.kpi.oop.game.controllers.MovableController;
import sk.tuke.kpi.oop.game.items.*;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;

public class FirstSteps implements SceneListener {
    private Ripley ellen;
    @Override
    public void sceneInitialized(@NotNull Scene scene) {
        ellen = new Ripley();
        scene.addActor(ellen, 0, 0);
        MovableController m = new MovableController(ellen);
        scene.getInput().registerListener(m);
        ellen.setAmmo(450);
        Energy energy = new Energy();
        scene.addActor(energy, 100, 100);
        Ammo ammo = new Ammo();
        scene.addActor(ammo, 200, 200);
        Ammo ammo2 = new Ammo();
        scene.addActor(ammo2, -100, -100);
        //new Move<>(Direction.SOUTHWEST, 1).scheduleFor(ellen);
        ellen.setEnergy(1);
        Hammer<Reactor> hammer = new Hammer<>();
        scene.addActor(hammer,150,150);
        Wrench<DefectiveLight> wrench = new Wrench<>();
        scene.addActor(wrench,50,50);
        FireExtinguisher<Reactor> fireExtinguisher = new FireExtinguisher<>();
        scene.addActor(fireExtinguisher, -50, -50);
        Mjolnir<Reactor> mjolnir = new Mjolnir<>();
        scene.addActor(mjolnir, -150, -150);
        KeeperController controller = new KeeperController(ellen);
        scene.getInput().registerListener(controller);

    }

    @Override
    public void sceneUpdating(@NotNull Scene scene) {
        int windowHeight = scene.getGame().getWindowSetup().getHeight();
        int yTextPos = windowHeight - GameApplication.STATUS_LINE_OFFSET;
        scene.getGame().getOverlay().drawText("| Energy: "+ellen.getEnergy()+" | Ammo: "+ellen.getAmmo(), 100 , yTextPos);
        scene.getGame().pushActorContainer(ellen.getBackpack());
    }
}*/
