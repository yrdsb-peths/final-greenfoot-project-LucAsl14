import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;
/**
 * Write a description of class Bomb here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bomb extends Bullet
{
    int frags = 20;
    boolean exploding = false;
    public Bomb(Tank own, double dir){
        super(own, dir);
        getImage().scale(20, 20);
    }
    public Bomb(boolean explode, int fragMult){
        super(null, 0);
        exploding = explode;
        frags*=fragMult;
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
    public void checkKeys(){
        if(owner==null) return;
        if(owner.hasShot()) return;
        if(owner.getColor()=="red"&&Greenfoot.isKeyDown("q")){
            exploding = true;
        }
        if(owner.getColor()=="green"&&Greenfoot.isKeyDown("m")){
            exploding = true;
        }
    }
    public void checkExplode(){
        Game world = (Game) getWorld();
        Random rand = new Random();
        if(!exploding) return;
        world.addObject(new Explosion(), getX(), getY());
        for(int i=0; i<frags; i++){
            int direction = rand.nextInt(360);
            world.addObject(new Fragment(direction), getX(), getY());
        }
        if(owner!=null) owner.bombExpire();
        world.removeObject(this);
    }
}
