import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;

/**
 * The first maze
 */
public class Maze1 extends Game
{
    int cx, cy;
    Tank red, green;
    Tank[] targets = new Tank[3];
    public Maze1(String gameType)
    {    
        super(gameType); 
        cx = getWidth()/2; cy = getHeight()/2;
        if(gameType=="multiplayer"){
            makeObjects2();
            makeLimits();
            makeScores2();
            makeMaze();
        } else if(gameType=="singleplayer"){
            makeObjects();
            makeLimits();
            makeScores();
            makeMaze();
        } else {
            Label problem = new Label("Something went wrong", 80);
            addObject(problem, cx, cy);
        }
    }
    private void makeObjects(){
        red = new Tank("red");
        Random rand = new Random();
        for(int i=0; i<3; i++){
            targets[i] = new Tank("blue");
            int x = rand.nextInt(13);
            int y = rand.nextInt(8);
            addObject(targets[i], x*75+150, y*75+37);
            targets[i].turn(rand.nextInt(360));
        }
        
        addObject(red, cx, cy+37);
        red.turn(rand.nextInt(360));
    }
    private void makeObjects2(){
        red = new Tank("red");
        green = new Tank("green");
        Random rand = new Random();
        
        addObject(red, cx-450+75, 75+37);
        addObject(green, cx+450-75, getHeight()-75-37);
        red.turn(rand.nextInt(360));
        green.turn(rand.nextInt(360));
    }
    
    private void makeMaze(){
        Random rand = new Random();
        for(int i=0; i<32; i++){
            int x = (rand.nextInt(13)+2)*75;
            int y = (rand.nextInt(7)+1)*75;
            addObject(new TopHorizontalWall(), x, y-2);
            addObject(new BottomHorizontalWall(), x, y+2);
            addObject(new LeftVerticalWall(5), x-37, y);
            addObject(new RightVerticalWall(5), x+37, y);
        }
        for(int i=0; i<32; i++){
            int x = (rand.nextInt(12)+2)*75+37;
            int y = rand.nextInt(8)*75+37;
            addObject(new LeftVerticalWall(), x-2, y);
            addObject(new RightVerticalWall(), x+2, y);
            addObject(new TopHorizontalWall(5), x, y-37);
            addObject(new BottomHorizontalWall(5), x, y+37);
        }
    }
}
