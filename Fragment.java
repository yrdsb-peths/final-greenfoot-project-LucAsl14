import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Fragment here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Fragment extends Bullet
{
    public final double velocity = super.velocity + 3;
    public Fragment(double dir){
        super(null, dir, true);
        lifeSpan*=2;
    }
    public void checkBounce(){
        World world = getWorld();
        if(getY()<=0||getY()>=getWorld().getHeight()-1){
            world.removeObject(this);
            return;
        }
            
        if(getX()<=0||getX()>=getWorld().getWidth()-1){
            world.removeObject(this);
            return;
        }
            
        if(isTouching(Wall.class)){
            world.removeObject(this);
            return;
        }        
    }
}
