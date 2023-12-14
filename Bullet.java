import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This is shot by tanks, can destroy tanks
 */
public class Bullet extends SmoothMover
{
    Tank owner;
    int velocity = 4;
    double vx, vy;
    int lifeSpan = 1000;
    boolean hasLeftOwner = false;
    /**
     * Creates a new bullet with an owner and a direction
     */
    public Bullet(Tank own, double dir){
        owner = own;
        vx = velocity*Math.cos(Math.toRadians(dir));
        vy = velocity*Math.sin(Math.toRadians(dir));
        getImage().scale(10, 10);
    }
    public void act()
    {
        Game world = (Game) getWorld();
        if(lifeSpan--==0){
            world.removeObject(this);
            return;
        }
        setLocation(getX()+vx, getY()+vy);
    }
}
