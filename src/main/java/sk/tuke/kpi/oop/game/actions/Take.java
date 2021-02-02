package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.items.Collectible;

import java.util.Objects;


public class Take<K extends Keeper> extends AbstractAction<K> {
    //private K player;
    public Take(){}
    /*public Take(K player){
         this.player = player;
    }*/

    @Override
    public void execute(float deltaTime) {
        if(getActor() != null) {
            try {
                for (Actor item : Objects.requireNonNull(getActor().getScene()).getActors()) {
                    if (getActor().intersects(item) && (item instanceof Collectible)) {
                        getActor().getBackpack().add((Collectible) item);
                        getActor().getScene().removeActor(item);
                        break;
                    }
                }
            }catch (IllegalStateException e){
                Objects.requireNonNull(getActor().getScene()).getGame().getOverlay().drawText(e.getMessage(), 0, 0).showFor(2);
            }finally {
                setDone(true);
            }
        }
        setDone(true);
    }
}
