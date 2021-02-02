package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.characters.Armed;
import sk.tuke.kpi.oop.game.Direction;
import sk.tuke.kpi.oop.game.weapons.Fireable;

import java.util.Objects;


public class Fire<F extends Armed> extends AbstractAction<F> {

    @Override
    public void execute(float deltaTime) {
        F shooter = getActor();
        if (Objects.nonNull(shooter) && !isDone()) {
            Fireable bullet = shooter.getFirearm().fire();
            if (bullet == null) {
                setDone(true);
                return;
            }
            bullet.getAnimation().setRotation(Direction.fromAngle(shooter.getAnimation().getRotation()).getAngle());
            int bulletX = shooter.getPosX() + 10;
            int bulletY = shooter.getPosY() + 10;
            Objects.requireNonNull(shooter.getScene()).addActor(bullet, bulletX, bulletY);
            new Move<Fireable>(Direction.fromAngle(shooter.getAnimation().getRotation()), Float.MAX_VALUE).scheduleFor(bullet);

        }
        setDone(true);
    }
}
