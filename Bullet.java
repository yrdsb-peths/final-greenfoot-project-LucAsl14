import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This is shot by tanks, can destroy tanks
 */
public class Bullet extends SmoothMover
{
    Tank owner;
    double direction;
    int velocity = 4;
    boolean hasLeftOwner = false;
    /**
     * Creates a new bullet with an owner and a direction
     */
    public Bullet(Tank own, double dir){
        owner = own;
        direction = dir;
    }
    public void act()
    {
        // Add your action code here.
    }
}
