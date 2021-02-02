package sk.tuke.kpi.oop.game;

public enum Direction {
    NORTHWEST(-1, 1),
    NORTH(0, 1),
    NORTHEAST(1, 1),
    WEST(-1, 0),
    EAST( 1, 0),
    SOUTHWEST(-1, -1),
    SOUTH(0, -1),
    SOUTHEAST(1, -1),
    NONE(0,0);

    private final int dx;
    private final int dy;

    Direction(int dx, int dy){
        this.dx=dx;
        this.dy=dy;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }

    public float getAngle(){
        if(dx == 1){
            if(dy == 1) return 315;
            else if(dy == -1) return 225;
            else return 270;
        }
        else if(dx == -1){
            if(dy == 1) return 45;
            else if(dy == -1) return 135;
            else return 90;
        }
        else{
            if(dy == 1) return 0;
            else if(dy == -1) return 180;
            else return -1;
        }

        /*
        if (dx==0 && dy==1){
            return 0;
        }
        else if (dx==1 && dy==0){
            return 270;
        }
        else if (dx==0 && dy==-1){
            return 180;
        }
        else if (dx==-1 && dy==0){
            return 90;
        }
        else if (dx==-1 && dy==1) return 45;
        else if (dx==-1 && dy==-1) return 135;
        else if (dx==1 && dy==-1) return 225;
        else if (dx==1 && dy==1) return 315;
        return -1;
        */
    }

    public Direction combine(Direction other) {
        int x,y;
        x=this.getDx() + other.getDx();
        y=this.getDy() + other.getDy();
        //zmena
        if(x>0) x=1;
        else if(x<0) x=-1;
        else x=0;
        if(y>0) y=1;
        else if(y<0) y=-1;
        else y=0;
        for(Direction c:Direction.values()){
            if (c.getDy() == y && c.getDx() == x) return c;
        }
        return NONE;
    }

    public static Direction fromAngle(float angle) {
        if(angle == 0){
            return Direction.NORTH;
        }else if(angle == 45){
            return Direction.NORTHWEST;
        }else if(angle == 90){
            return Direction.WEST;
        }else if(angle == 135){
            return Direction.SOUTHWEST;
        }else if(angle == 180){
            return Direction.SOUTH;
        }else if(angle == 225){
            return Direction.SOUTHEAST;
        }else if(angle == 270){
            return Direction.EAST;
        }else if(angle == 315){
            return Direction.NORTHEAST;
        }else{
            return Direction.NONE;
        }
    }
}
