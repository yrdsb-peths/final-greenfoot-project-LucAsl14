import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Explosion here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Explosion extends Actor
{
    GreenfootImage[] sprites = new GreenfootImage[16];
    public Explosion(){
        for(int i=0; i<16; i++){
            sprites[i] = new GreenfootImage("explosion/explosion"+i+".png");
        }
        setImage(sprites[0]);
    }
    
    int aniFrames = 0;
    int timeElapsed = 0;
    public void act()
    {
        timeElapsed++;
        if(timeElapsed%4==0){
            timeElapsed%=4;
            aniFrames++;
            if(aniFrames>=16){
                getWorld().removeObject(this);
                return;
            }
            setImage(sprites[aniFrames]);
            System.out.println(aniFrames);
        }
    }
}
