package sk.tuke.kpi.oop.game.weapons;

public class Gun extends Firearm{
    public Gun(int init, int max){
        super(init,max);
    }

    @Override
    protected Fireable createBullet() {
        return new Bullet();
    }
}
