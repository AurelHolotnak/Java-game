package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Actor;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.List;
import java.util.Objects;


public class ChainBomb extends TimeBomb{

    public ChainBomb(float s){
        super(s);
    }

    @Override
    public void explosion() {
        super.explosion();
        Ellipse2D.Float area = new Ellipse2D.Float(this.getPosX()-50, this.getPosY()-50,101,101);
        @NotNull List<Actor> actors= Objects.requireNonNull(getScene()).getActors();
        for(Actor b:actors) {
            if (b instanceof ChainBomb ) {
                Rectangle2D bomb = new Rectangle2D.Float(b.getPosX(), b.getPosY(), 16, 16);
                if (area.intersects(bomb)){
                    ((ChainBomb) b).activate();
                }
            }
        }


    }
}
