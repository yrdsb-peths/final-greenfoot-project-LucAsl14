import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class verticalWall here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class VerticalWall extends Wall
{
    public VerticalWall(){
        this(75);
    }
    public VerticalWall(int y){
        getImage().scale(5, y);
    }
}
