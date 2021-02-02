package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.Player;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;

import java.util.Objects;

public class Teleport extends AbstractActor {

    private Teleport destinationTeleport;
    private Boolean justEntered;

    @Nullable
    @Override
    public Scene getScene() {

        return super.getScene();
    }
    public Player getPlayer() {

        return (Player) Objects.requireNonNull(getScene()).getFirstActorByName("Player");
    }


    public Teleport(Teleport destinationTeleport) {
        Animation lift = new Animation("sprites/lift.png", 48, 48, 100, Animation.PlayMode.LOOP_PINGPONG);
        this.setAnimation(lift);
        this.destinationTeleport = destinationTeleport;
        this.justEntered = true;
    }

    public void setDestination(Teleport destinationTeleport) {
        if (destinationTeleport != this)
            this.destinationTeleport = destinationTeleport;
    }
    public Teleport getDestination(){
        return destinationTeleport;
    }
    //@Override
    public void act() {
        if (!this.intersects(this.getPlayer()) && this.justEntered)
            this.justEntered = false;
        if (isPlayerIn() && !this.justEntered && this.destinationTeleport != null) {
            this.destinationTeleport.teleportPlayer(getPlayer());
        }
    }

    public void teleportPlayer(Player player) {
        player.setPosition(this.getPosX() + 8, this.getPosY() + 8);
        this.justEntered = true;
    }

    private Boolean isPlayerIn() {
        int px = this.getPlayer().getPosX() + this.getPlayer().getAnimation().getWidth()/2;
        int py = this.getPlayer().getPosY() + this.getPlayer().getAnimation().getHeight()/2;

        return px > this.getPosX() && px < this.getPosX() + 48 && py > this.getPosY() && py < this.getPosY() + 48;
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        new Loop<>(new Invoke<>(this::act)).scheduleFor(this);
    }
}

