import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * further categorization of walls
 */
public class LeftVerticalWall extends VerticalWall
{
    public LeftVerticalWall(){
        this(75);
    }
    public LeftVerticalWall(int y){
        length = y;
        getImage().scale(width, length);
    }
}
