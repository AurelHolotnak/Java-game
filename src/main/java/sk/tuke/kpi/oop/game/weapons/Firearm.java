package sk.tuke.kpi.oop.game.weapons;

public abstract class Firearm {
    private int actual;
    private int max;
    public Firearm(int init, int max){
        this.actual = init;
        this.max = max;
    }
    public Firearm(int max){
        this.actual = max;
        this.max = max;
    }
    public int getAmmo(){return this.actual;}

    public void reload(int newAmmo){
        this.actual += newAmmo;
        if(this.actual > this.max){
            this.actual = this.max;
        }
    }

    public Fireable fire(){
        if(this.actual > 0) {
            this.actual -= 1;
            return this.createBullet();
        } else {
            return null;
        }

    }
    protected abstract Fireable createBullet();//skusit potom vymenit abstract a protected
}
