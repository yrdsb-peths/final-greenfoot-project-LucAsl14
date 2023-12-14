import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The first maze
 */
public class Maze1 extends Game
{
    int cx, cy;
    public Maze1(String gameType)
    {    
        super(gameType); 
        cx = getWidth()/2; cy = getHeight()/2;
        if(gameType=="multiplayer"){
            Tank red = new Tank("red");
            Tank green = new Tank("green");
        
            addObject(red, cx-300, cy);
            addObject(green, cx+300, cy);
            green.turn(180);
        }
    }
}
