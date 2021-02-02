package sk.tuke.kpi.oop.game.controllers;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.Input;
import sk.tuke.kpi.gamelib.KeyboardListener;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.actions.Drop;
import sk.tuke.kpi.oop.game.actions.Shift;
import sk.tuke.kpi.oop.game.actions.Take;
import sk.tuke.kpi.oop.game.actions.Use;
import sk.tuke.kpi.oop.game.items.Usable;

import java.util.Objects;

public class KeeperController implements KeyboardListener {
    private Keeper actor;

    public KeeperController(Keeper actor){
        this.actor = actor;
    }
    public void tlacitkoU(){
        for (Actor item : Objects.requireNonNull(this.actor.getScene()).getActors()) {
            if (item instanceof Usable && this.actor.intersects(item)) {
                new Use<>((Usable<?>) item).scheduleForIntersectingWith(this.actor);
            }
        }
    }
    @Override
    public void keyPressed(@NotNull Input.Key key) {
        /*
        switch (key){
            case ENTER:
                new Take<>(actor).scheduleFor(this.actor);
                break;
            case BACKSPACE:
                new Drop<>(actor).scheduleFor(this.actor);
                break;
            case S:
                new Shift<>(actor).scheduleFor(this.actor);
                break;
            case U:
                for (Actor item : Objects.requireNonNull(this.actor.getScene()).getActors()) {
                    if (item instanceof Usable && this.actor.intersects(item)) {
                        new Use<>((Usable<?>) item).scheduleForIntersectingWith(this.actor);
                    }
                }
                break;
            case B:
                if(this.actor.getBackpack().peek() instanceof Usable) {
                    new Use<>((Usable<?>) actor.getBackpack().peek()).scheduleForIntersectingWith(actor);
                    this.actor.getBackpack().remove(Objects.requireNonNull(this.actor.getBackpack().peek()));
                }
                break;
            default:
                break;
        }
       */
        //Take<>(actor), pred to dat if actor != null, vratit konstruktor do akcii
        if(Input.Key.ENTER == key) new Take<>().scheduleFor(this.actor);
        if(Input.Key.BACKSPACE == key) new Drop<>().scheduleFor(this.actor);
        if(Input.Key.S == key) new Shift<>().scheduleFor(this.actor);
        if(Input.Key.U == key){
            tlacitkoU();
        }
        if (Input.Key.B == key && this.actor.getBackpack().peek() instanceof Usable) {
            new Use<>((Usable<?>) this.actor.getBackpack().peek()).scheduleForIntersectingWith(this.actor);
            this.actor.getBackpack().remove(Objects.requireNonNull(this.actor.getBackpack().peek()));
        }
    }
}
