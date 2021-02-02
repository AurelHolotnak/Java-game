package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.Movable;
import sk.tuke.kpi.gamelib.actions.Action;

import java.util.Objects;

public class Move<A extends Movable> implements Action<A> {
    private A actor;
    private boolean first;
    private float passed;
    private float duration;
    private boolean hotovo;
    private Direction dir;
    //private Movable movable;
    private int pX;
    private int pY;

    public Move(Direction direction, float duration) {
        this.dir = direction;
        this.duration = duration;
        this.first = true;
        this.hotovo = false;
        this.passed = 0;
        this.actor = getActor();
    }
    public Move(Direction direction) {
        this(direction, 0);
    }


    @Override
    public A getActor() {
        return this.actor;
    }

    @Override
    public void setActor(A actor) {
        this.actor = actor;
    }

    @Override
    public boolean isDone() {
        return this.hotovo;
    }

    @Override
    public void execute(float deltaTime) {
        if (!isDone()) {
            //duration = duration - deltaTime;

            if(first){
                this.first = false;
                actor.startedMoving(dir);
            }

            this.passed += deltaTime;
            if (this.duration - passed >= 1e-5 ){
                pX = actor.getPosX();
                pY = actor.getPosY();
                this.actor.setPosition(actor.getPosX() + dir.getDx() * actor.getSpeed(),
                    actor.getPosY() + dir.getDy()  * actor.getSpeed());
                if(Objects.requireNonNull(actor.getScene()).getMap().intersectsWithWall(actor)) {
                    this.stop();
                    this.actor.setPosition(pX, pY);
                    actor.collidedWithWall();
                }
                /*if(actor instanceof Bullet){
                    for (Actor a:actor.getScene().getActors()) {
                        if (a.intersects(actor) && a instanceof Enemy) {
                            this.stop();
                            actor.collidedWithWall();
                            ((Alive) a).getHealth().drain(50);
                            return;
                        }
                    }

                }*/
            }
            else{
                this.stop();
            }
        }


        /*if(first) {
            movable.startedMoving(Direction.WEST);
            first = false;
        }

        this.passed += deltaTime;
        if ((this.passed-this.duration)>= 1e-5){
            this.hotovo=true;
        }
        if(isDone()) movable.stoppedMoving();
*/
    }

    public void stop(){
        if(this.actor == null) return;
        this.actor.stoppedMoving();
        reset();
        this.hotovo = true;

    }
    @Override
    public void reset() {
        first = true;
        hotovo = false;
    }
}
