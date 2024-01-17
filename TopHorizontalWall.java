import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * further categorization of walls
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
