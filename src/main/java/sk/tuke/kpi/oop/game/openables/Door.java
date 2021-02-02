package sk.tuke.kpi.oop.game.openables;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.map.MapTile;

import sk.tuke.kpi.gamelib.messages.Topic;
import sk.tuke.kpi.oop.game.items.Usable;
import sk.tuke.kpi.gamelib.Scene;

import java.util.Objects;

public class Door extends AbstractActor implements Openable, Usable<Actor> {
    private boolean opened;
    private Orientation orientation;
    public static final Topic<Door> DOOR_OPENED = Topic.create("door opened", Door.class);
    public static final Topic<Door> DOOR_CLOSED = Topic.create("door closed", Door.class);
    public enum Orientation {
        HORIZONTAL,
        VERTICAL
    }

    public Door(String name, Orientation orientation){
        super(name);
        opened = false;
        this.orientation = orientation;
        if (orientation == Orientation.VERTICAL) setAnimation(new Animation("sprites/vdoor.png", 16, 32, 0.1f, Animation.PlayMode.ONCE));
        else setAnimation(new Animation("sprites/hdoor.png", 32, 16, 0.1f, Animation.PlayMode.ONCE));



    }
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        close();
    }

    public void zamuruj(){
        MapTile stena1;
        MapTile stena2;
        if(orientation == Orientation.VERTICAL) {
            stena1 = Objects.requireNonNull(getScene()).getMap().getTile(
                this.getPosX() / getScene().getMap().getTile(0, 0).getWidth(),
                this.getPosY() / getScene().getMap().getTile(0, 0).getHeight());
            stena2 = Objects.requireNonNull(getScene()).getMap().getTile(
                this.getPosX() / getScene().getMap().getTile(0, 0).getWidth(),
                this.getPosY() / getScene().getMap().getTile(0, 0).getHeight()+1);
        }
        else{
            stena1 = Objects.requireNonNull(getScene()).getMap().getTile(
                this.getPosX() / getScene().getMap().getTile(0, 0).getWidth(),
                this.getPosY() / getScene().getMap().getTile(0, 0).getHeight());
            stena2 = Objects.requireNonNull(getScene()).getMap().getTile(
                this.getPosX() / getScene().getMap().getTile(0, 0).getWidth()+1,
                this.getPosY() / getScene().getMap().getTile(0, 0).getHeight());
        }
        if(isOpen()) {
            stena1.setType(MapTile.Type.CLEAR);
            stena2.setType(MapTile.Type.CLEAR);
        }
        else {
            stena1.setType(MapTile.Type.WALL);
            stena2.setType(MapTile.Type.WALL);
        }

    }
    @Override
    public void open() {
        opened = true;
        zamuruj();
        getAnimation().play();
        getAnimation().setPlayMode(Animation.PlayMode.ONCE);
        Objects.requireNonNull(getScene()).getMessageBus().publish(DOOR_OPENED, this);

    }

    @Override
    public void close() {
        opened = false;
        zamuruj();
        getAnimation().setPlayMode(Animation.PlayMode.ONCE_REVERSED);
        getAnimation().play();
        Objects.requireNonNull(getScene()).getMessageBus().publish(DOOR_CLOSED, this);
    }

    @Override
    public boolean isOpen() {
        return opened;
    }

    @Override
    public void useWith(Actor actor) {
        if (isOpen()) {
            close();
        } else {
            open();
        }

    }

    @Override
    public Class<Actor> getUsingActorClass() {
        return Actor.class;
    }
}
