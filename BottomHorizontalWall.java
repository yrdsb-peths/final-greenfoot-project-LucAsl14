import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * further categorization of walls
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
