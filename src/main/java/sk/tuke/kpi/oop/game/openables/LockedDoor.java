package sk.tuke.kpi.oop.game.openables;

import sk.tuke.kpi.gamelib.Actor;

public class LockedDoor extends Door {
    private boolean locked;
    public LockedDoor(String name, Orientation orientation){
        super(name, orientation);
        this.locked = false;
    }

    public void lock(){
        this.locked = true;
        //super.close();
    }

    public void unlock(){
        this.locked = false;
        //super.open();
    }

    @Override
    public void useWith(Actor actor) {
        if(!locked) super.useWith(actor);
    }

    public boolean isLocked(){
        return locked;
    }
}
