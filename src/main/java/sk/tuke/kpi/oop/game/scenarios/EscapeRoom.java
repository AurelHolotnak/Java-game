package sk.tuke.kpi.oop.game.scenarios;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.*;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.oop.game.Locker;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.oop.game.Ventilator;
import sk.tuke.kpi.oop.game.behaviours.Behaviour;
import sk.tuke.kpi.oop.game.behaviours.Observing;
import sk.tuke.kpi.oop.game.behaviours.RandomlyMoving;
import sk.tuke.kpi.oop.game.characters.Alien;
import sk.tuke.kpi.oop.game.characters.AlienMother;
import sk.tuke.kpi.oop.game.characters.Ripley;
import sk.tuke.kpi.oop.game.controllers.KeeperController;
import sk.tuke.kpi.oop.game.controllers.MovableController;
import sk.tuke.kpi.oop.game.controllers.ShooterController;
import sk.tuke.kpi.oop.game.items.AccessCard;
import sk.tuke.kpi.oop.game.items.Ammo;
import sk.tuke.kpi.oop.game.items.Energy;
import sk.tuke.kpi.oop.game.openables.Door;
import sk.tuke.kpi.oop.game.openables.LockedDoor;

import java.util.Objects;
import java.util.function.Predicate;

public class EscapeRoom implements SceneListener {
    private Ripley ellen;
    private Disposable pohyb;
    private Disposable kontroler;
    private Disposable shooter;
    @Override
    public void sceneCreated(@NotNull Scene scene) {
        /*SceneListener.super.sceneCreated(scene);
        scene.getMessageBus().subscribe(World.ACTOR_ADDED_TOPIC, add->{
                if (add instanceof Alien){
                    ((Alien) add).randMove();
                    //System.out.println("alien");
                }
            }
        );*/
    }

    @Override
    public void sceneInitialized(@NotNull Scene scene) {
        for (Actor actor : scene.getActors()) {
            if (actor instanceof Ripley) {
                ellen = (Ripley) actor;
                //actor.setPosition(150,50);
                KeeperController kc = new KeeperController((Ripley)actor);
                kontroler = scene.getInput().registerListener(kc);
                MovableController mc = new MovableController((Ripley)actor);
                pohyb = scene.getInput().registerListener(mc);
                ShooterController sc = new ShooterController((Ripley)actor);
                shooter = scene.getInput().registerListener(sc);
                scene.follow(actor);
            }
            //if (actor instanceof Door){
            //((Door) actor).close();
            //((Door) actor).open();
            //}
        }
    }
    @Override
    public void sceneUpdating(@NotNull Scene scene) {
        /*int windowHeight = scene.getGame().getWindowSetup().getHeight();
        int yTextPos = windowHeight - GameApplication.STATUS_LINE_OFFSET;
        scene.getGame().getOverlay().drawText("| Energy: "+ Objects.requireNonNull(this.ellen).getHealth().getValue()+" | Ammo: "+ellen.getAmmo(), 100 , yTextPos);
        scene.getGame().pushActorContainer(ellen.getBackpack());
        */ellen.showRipleyState();
    }
    public static class Factory implements ActorFactory {

        @Override
        public @Nullable Actor create(@Nullable String type, @Nullable String name) {
            RandomlyMoving random = new RandomlyMoving();
            Predicate<Door> predicate = new Predicate<>() {
                @Override
                public boolean test(Door door) {
                    return door.isOpen();
                }
            };
            Observing<Movable, Door> wakeup = new Observing<>(Door.DOOR_OPENED, predicate, new RandomlyMoving());
            if(Objects.equals(name, "ellen")) return new Ripley();
            else if("energy".equals(name)) return new Energy();
            else if("ammo".equals(name)) return new Ammo();
            else if("alien".equals(name)) {
                if("running".equals(type))
                return new Alien(random);
                else return new Alien(wakeup);
            }
            else if("back door".equals(name)) return new LockedDoor("hdoor", Door.Orientation.HORIZONTAL);
            else if("front door".equals(name)) return new LockedDoor("vdoor", Door.Orientation.VERTICAL);
            else if("access card".equals(name))return new AccessCard();
            else if("locker".equals(name)) return new Locker();
            else if("ventilator".equals(name)) return new Ventilator();
            else if("alien mother".equals(name)) return new AlienMother(random);
            else return null;
        }
    }
}
