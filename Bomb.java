import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;
/**
 * A bomb is a larger bullet that can be triggered to explode on command
 */
public class Bomb extends Bullet
{
    int frags = 20;
    final double velocity = super.velocity - 1;
    boolean exploding = false;
    boolean funny = false;
    /**
     * makes a new bomb with an owner and a direction
     */
    public Bomb(Tank own, double dir){
        super(own, dir);
        getImage().scale(20, 20);
        lifeSpan*=1.5;
    }
    /**
     * makes a special bomb that has no owner and direction
     */
    public Bomb(boolean explode, double fragMult, boolean funny){
        super(null, 0);
        exploding = explode;
        frags*=fragMult;
        this.funny = funny;
    }
    public void addedToWorld(){
        checkExplode();
    }
    public void act()
    {
        turn(10);
        Game world = (Game) getWorld();
        // when bullet runs out of lifespan
        if(lifeSpan--==0){
            exploding = true;
        }
        
        move();
        checkKill();
        checkKeys();
        checkBounce();
        
        checkExplode();
    }
    /**
     * checks whether the trigger for exploding has been pressed
     */
    private void checkKeys(){
        if(owner==null) return;
        if(owner.hasShot()) return;
        if(owner.getColor()=="red"&&Greenfoot.isKeyDown("q")){
            exploding = true;
        }
        if(owner.getColor()=="green"&&Greenfoot.isKeyDown("m")){
            exploding = true;
        }
    }
    /**
     * checks for the exploding flag, then explodes if it should
     */
    private void checkExplode(){
        // if not exploding, then there's no need to run the rest
        if(!exploding) return;
        Game world = (Game) getWorld();
        Random rand = new Random();
        // if it's funny, then we make a soundless explosion to not hurt our ears
        if(!funny) world.addObject(new Explosion(), getX(), getY());
        else world.addObject(new Explosion(32), getX(), getY());
        // spawns frags in random directions
        for(int i=0; i<frags; i++){
            int direction = rand.nextInt(360);
            world.addObject(new Fragment(direction), getX(), getY());
        }
        // trigger bomb expiration on the tank that shot it if it didn't come
        // from a trap
        if(owner!=null) owner.bombExpire();
        world.removeObject(this);
    }
}
