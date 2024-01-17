import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * the drill is a special bullet that delets walls instead of bouncing
 */
public class Drill extends Bullet
{
    /**
     * makes a drill that has more speed than a 
     */
    public Drill(Tank own, double dir){
        super(own, dir, true);
        getImage().scale(8, 8);
        vx *= 2.5; vy *= 2.5;
    }
    
    /**
     * Override of checkBounce to delete wall instead
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
