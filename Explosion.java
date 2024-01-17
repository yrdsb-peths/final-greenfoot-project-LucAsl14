import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * An explosion class that makes explosions happen!
 */
public class Explosion extends Actor
{
    GreenfootImage[] sprites = new GreenfootImage[16];
    GreenfootSound boom = new GreenfootSound("explosion.wav");
    boolean noSound = false;
    /**
     * constructor for Explosion, also initializes the sprites
     */
    public Explosion(){
        for(int i=0; i<16; i++){
            sprites[i] = new GreenfootImage("explosion/explosion"+i+".png");
        }
        setImage(sprites[0]);
    }
    /**
     * alternate constructor for Explosion, which, in addition to the previous
     * constructor, also changes the explosion size and removes its sound
     */
    public Explosion(int imageSize){
        for(int i=0; i<16; i++){
            sprites[i] = new GreenfootImage("explosion/explosion"+i+".png");
            sprites[i].scale(imageSize, imageSize);
        }
        setImage(sprites[0]);
        noSound = true;
    }
    public void addedToWorld(World world){
        // makes a sound when added to the world
        if(!noSound) boom.play();
    }
    int aniFrames = 0;
    int timeElapsed = 0;
    public void act()
    {
        // animates the explosion
        timeElapsed++;
        if(timeElapsed%4==0){
            timeElapsed%=4;
            aniFrames++;
            if(aniFrames>=16){
                getWorld().removeObject(this);
                return;
            }
            setImage(sprites[aniFrames]);
        }
    }
}
