import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class verticalWall here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LeftVerticalWall extends Wall
{
    public LeftVerticalWall(){
        this(75);
    }
    public LeftVerticalWall(int y){
        length = y;
        getImage().scale(width, length);
    }
}
