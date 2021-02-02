package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.actions.Invoke;

import sk.tuke.kpi.gamelib.framework.Player;

import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.gamelib.framework.AbstractActor;

import java.util.Objects;

public class Helicopter extends AbstractActor {

    private boolean activated;


    public Helicopter(){
        activated=false;
        setAnimation(new Animation("sprites/heli.png",
            64,
            64,
            0.1f,
            Animation.PlayMode.LOOP));

    }
    public Player getPlayer(){
        return (Player) Objects.requireNonNull(getScene()).getLastActorByName("Player");

    }
    public void move(int x, int y){
        int smerX=0;
        int smerY=0;
        if(activated) {
        /*if(x==this.getPosX()){
            smerX=0;
        }
        if(y==this.getPosY() ){
            smerY=0;
        }*/
            if (x > this.getPosX()) {
                smerX = 1;
                this.setPosition(this.getPosX() + smerX, this.getPosY());
            }
            else if (x < this.getPosX()) {
                smerX = -1;
                this.setPosition(this.getPosX() + smerX, this.getPosY());
            }
            if (y > this.getPosY()) {
                smerY = 1;
                this.setPosition(this.getPosX(), this.getPosY()+smerY);
            }
            else if (y < this.getPosY()) {
                smerY = -1;
                this.setPosition(this.getPosX(), this.getPosY()+smerY);
            }

        }
        if(this.intersects(getPlayer())){
            getPlayer().setEnergy(getPlayer().getEnergy()-1);
        }
    }
    public void searchAndDestroy(){
        activated=true;
        /*disposable = */new Loop<>(
            new Invoke<>(
                () -> move(getPlayer().getPosX(), getPlayer().getPosY())
            )
        ).scheduleFor(this);
    }
}
