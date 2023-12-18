import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class wall here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Wall extends Actor
{
    int length, width = 4;
    public void addedToWorld(World world){
        if(isTouching(Tank.class)){
            world.removeObject(this);
            return;
        }
            
        if(!isTouching(Wall.class)&&length<7)
            world.removeObject(this);
    }
}
