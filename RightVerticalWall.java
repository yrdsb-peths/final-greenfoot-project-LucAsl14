import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * further categorization of walls
 */
public class RightVerticalWall extends VerticalWall
{
    public RightVerticalWall(){
        this(75);
    }
    public RightVerticalWall(int y){
        length = y;
        getImage().scale(width, length);
    }
}
