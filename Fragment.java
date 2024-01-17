import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * fragments are created after a trap or bomb explosion. They do not bounce
 * on walls, but instead get deleted
 */
public class Fragment extends Bullet
{
    public final double velocity = super.velocity + 3;
    /**
     * creates a fragment with more lifespan than a standard bullet
     */
    public Fragment(double dir){
        super(null, dir, true);
        lifeSpan*=2;
    }
    /**
     * override of Bullet's checkBounce to not bounce on walls
     */
    protected void checkBounce(){
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
