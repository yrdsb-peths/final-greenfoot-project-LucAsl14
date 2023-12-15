import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class horizontalWall here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HorizontalWall extends Wall
{
    public HorizontalWall(){
        this(75);
    }
    public HorizontalWall(int x){
        type = "horizontal";
        getImage().scale(x, 5);        
    }
}
