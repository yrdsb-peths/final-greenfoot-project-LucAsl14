import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class rightVerticalWall here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RightVerticalWall extends Wall
{
    public RightVerticalWall(){
        this(75);
    }
    public RightVerticalWall(int y){
        length = y;
        getImage().scale(width, length);
    }
}
