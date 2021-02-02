/*package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.*;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;

import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.oop.game.Locker;
import sk.tuke.kpi.oop.game.Ventilator;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.controllers.KeeperController;
import sk.tuke.kpi.oop.game.controllers.MovableController;
import sk.tuke.kpi.oop.game.items.AccessCard;
import sk.tuke.kpi.oop.game.items.Energy;
import sk.tuke.kpi.oop.game.openables.Door;
import sk.tuke.kpi.oop.game.openables.LockedDoor;

import java.util.Objects;

public class MissionImpossible implements SceneListener {
    private Ripley ellen;
    private Disposable kontroler;
    private Disposable pohyb;
    private Disposable tox;
    //private boolean contamined;
    @Override
    public void sceneInitialized(@NotNull Scene scene) {
        //contamined = false;
        ellen = (Ripley)new Factory().create("ellen", "ellen");
        scene.addActor(Objects.requireNonNull(new Factory().create("door", "door")), 95,63);
        scene.addActor(Objects.requireNonNull(new Factory().create("access card", "access card")), 150,60);
        scene.addActor(ellen);
        scene.addActor(Objects.requireNonNull(new Factory().create("ventilator", "ventilator")), 15,63);
        scene.addActor(Objects.requireNonNull(new Factory().create("locker", "locker")), 150,60);
        for (Actor actor : scene.getActors()) {
            if (actor instanceof Ripley) {
                actor.setPosition(150,50);
                KeeperController kc = new KeeperController((Ripley)actor);
                kontroler = scene.getInput().registerListener(kc);
                MovableController mc = new MovableController((Ripley)actor);
                pohyb = scene.getInput().registerListener(mc);
                scene.follow(actor);
            }
            //if (actor instanceof Door){
                //((Door) actor).close();
                //((Door) actor).open();
            //}
        }
        scene.getMessageBus().subscribe(Door.DOOR_OPENED, door1 -> {
                //when contamined - ak uz raz boli dvere otvoren
                tox = new Loop<>(
                        new ActionSequence<>(
                            new Wait<>(1),
                            new Invoke<>(
                                ()->ellen.toxic()
                            )
                        )
                ).scheduleFor(ellen);
            }
        );
        scene.getMessageBus().subscribe(Ripley.RIPLEY_DIED, ellen1 ->{
                kontroler.dispose();
                pohyb.dispose();
            }
        );
        scene.getMessageBus().subscribe(Ventilator.VENTILATOR_REPAIRED, ventilator1 -> {
                tox.dispose();
            }
        );
    }

    @Override
    public void sceneUpdating(@NotNull Scene scene) {
        /*int windowHeight = scene.getGame().getWindowSetup().getHeight();
        int yTextPos = windowHeight - GameApplication.STATUS_LINE_OFFSET;
        scene.getGame().getOverlay().drawText("| Energy: "+ Objects.requireNonNull(ellen).getEnergy()+" | Ammo: "+ellen.getAmmo(), 100 , yTextPos);
        scene.getGame().pushActorContainer(ellen.getBackpack());
        ellen.showRipleyState();


    }

    public static class Factory implements ActorFactory{

        @Override
        public @Nullable Actor create(@Nullable String type, @Nullable String name) {
            if(name=="ellen") return new Ripley();
            else if("energy"==name) return new Energy();
            else if("door"==name) return new LockedDoor(Door.Orientation.HORIZONTAL);
            else if("access card"==name)return new AccessCard();
            else if("locker"==name) return new Locker();
            else if("ventilator"==name) return new Ventilator();
            else return null;
        }
    }
}*/
