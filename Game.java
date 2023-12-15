import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Game extends World
{
    String gameType;
    boolean createdGame = false;
    int cx, cy;
    public Game(String gameType){    
        super(1200, 600, 1);
        cx = getWidth()/2; cy = getHeight()/2;
        this.gameType = gameType;
    }
    public void newGame(){
        Greenfoot.setWorld(new Maze1(gameType));
    }
    protected void makeLimits(){
        addObject(new VerticalWall(getHeight()), cx-500, cy);
        addObject(new VerticalWall(getHeight()), cx+500, cy);
    }
    protected void makeScores(){
        Label redTankSprite = new Label(new GreenfootImage("redTank_base.png"));
        Label greenTankSprite = new Label(new GreenfootImage("greenTank_base.png"));
        Counter redScoreCounter = new Counter();
        Counter greenScoreCounter = new Counter();
        
        addObject(redTankSprite, 50, cy-100);
        addObject(greenTankSprite, getWidth()-50, cy-100);
        addObject(redScoreCounter, 50, cy+100);
        addObject(greenScoreCounter, 50, cy+100);
        redTankSprite.turn(90);
        redTankSprite.getImage().scale(45, 30);
        greenTankSprite.turn(90);
        greenTankSprite.getImage().scale(45, 30);
    }
}
