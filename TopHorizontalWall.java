import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TopHoriozontalWall here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TopHorizontalWall extends HorizontalWall
{
    public TopHorizontalWall(){
        this(75);
    }
    public TopHorizontalWall(int x){
        length = x;
        getImage().scale(length, width); 
    }
}
