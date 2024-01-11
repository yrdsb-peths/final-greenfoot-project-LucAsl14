import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Drill extends Bullet
{
    public Drill(Tank own, double dir){
        super(own, dir, true);
        getImage().scale(8, 8);
        vx *= 2; vy *= 2;
    }
    
    /**
     * Override of checkBounce to not delete wall instead
     */
    public void checkBounce(){
        if(isTouching(Wall.class)){
            Wall wall = (Wall) getOneIntersectingObject(Wall.class);
            if(wall.removeSelf()){
                getWorld().removeObject(this);
                return;
            }
        }
    }
}
