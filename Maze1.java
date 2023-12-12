import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The first maze
 */
public class Maze1 extends World
{
    int cx, cy;
    public Maze1(String gameType)
    {    
        super(1200, 600, 1); 
        cx = getWidth()/2; cy = getHeight()/2;
        
        if(gameType=="multiplayer"){
            Tank red = new Tank("red");
            Tank green = new Tank("green");
        
            addObject(red, cx-400, cy);
            addObject(green, cx+400, cy);
        }
    }
}
