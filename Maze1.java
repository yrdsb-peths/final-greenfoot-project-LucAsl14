import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;

/**
 * The first maze
 */
public class Maze1 extends Game
{
    int cx, cy;
    Tank red, green;
    public Maze1(String gameType)
    {    
        super(gameType); 
        cx = getWidth()/2; cy = getHeight()/2;
        if(gameType=="multiplayer"){
            makeObjects2();
        }
        makeLimits();
        makeScores();
        makeMaze();
    }
    
    private void makeObjects2(){
        red = new Tank("red");
        green = new Tank("green");
    
        addObject(red, cx-300, cy);
        addObject(green, cx+300, cy);
        green.turn(180);   
    }
    
    private void makeMaze(){
        Random rand = new Random();
        for(int i=0; i<30; i++){
            int x = rand.nextInt(13)*75+112+38;
            int y = (rand.nextInt(7)+1)*75;
            addObject(new HorizontalWall(), x, y);
        }
        for(int i=0; i<30; i++){
            int x = (rand.nextInt(12)+1)*75+112;
            int y = rand.nextInt(8)*75+37;
            addObject(new VerticalWall(), x, y);
        }
    }
}
