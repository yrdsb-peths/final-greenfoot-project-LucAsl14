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
            makeLimits();
            makeScores();
            makeMaze();
        } else {
            Label pity = new Label("Go get a friend ;)", 50);
            addObject(pity, cx, cy);
        }
    }
    
    private void makeObjects2(){
        red = new Tank("red");
        green = new Tank("green");
        Random rand = new Random();
        
        addObject(red, cx-450+37, 75);
        addObject(green, cx+450-37, getHeight()-75);
        red.turn(rand.nextInt(360));
        green.turn(rand.nextInt(360));
    }
    
    private void makeMaze(){
        Random rand = new Random();
        for(int i=0; i<32; i++){
            int x = rand.nextInt(13)*75+112+38;
            int y = (rand.nextInt(7)+1)*75;
            addObject(new TopHorizontalWall(), x, y-2);
            addObject(new BottomHorizontalWall(), x, y+2);
            addObject(new LeftVerticalWall(4), x-37, y);
            addObject(new RightVerticalWall(4), x+37, y);
        }
        for(int i=0; i<32; i++){
            int x = (rand.nextInt(12)+1)*75+112;
            int y = rand.nextInt(8)*75+37;
            addObject(new LeftVerticalWall(), x-2, y);
            addObject(new RightVerticalWall(), x+2, y);
            addObject(new TopHorizontalWall(4), x, y-37);
            addObject(new BottomHorizontalWall(4), x, y+37);
        }
    }
}
