import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class horizontalWall here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BottomHorizontalWall extends HorizontalWall
{
    public BottomHorizontalWall(){
        this(75);
    }
    public BottomHorizontalWall(int x){
        length = x;
        getImage().scale(length, width); 
    }
}
