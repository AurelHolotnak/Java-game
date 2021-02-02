package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Keeper;
import sk.tuke.kpi.oop.game.items.Collectible;

import java.util.Objects;


public class Drop<K extends Keeper> extends AbstractAction<K> {
    //private K player;

    public Drop(){}
    /*public Drop(K player){
        this.player = player;
    }*/

    @Override
    public void execute(float deltaTime) {
        if(getActor() != null) {
            try {
                Collectible item = getActor().getBackpack().peek();
                if(item != null) {
                    Objects.requireNonNull(getActor().getScene()).addActor(Objects.requireNonNull(item), getActor().getPosX(), getActor().getPosY());
                    getActor().getBackpack().remove(item);
                }
            }catch (IllegalStateException e) {
                Objects.requireNonNull(getActor().getScene()).getGame().getOverlay().drawText(e.getMessage(), 0, 0).showFor(2);
            }
            finally {
                setDone(true);
            }
        }
        setDone(true);
    }
}
