import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Game extends World
{
    String gameType;
    public boolean redDied = false, greenDied = false;
    boolean counting = false, ended = false;;
    SimpleTimer timer = new SimpleTimer();
    int cx, cy;
    static Counter redScoreCounter = new Counter();
    static Counter greenScoreCounter = new Counter();
    public Game(String gameType){    
        super(1200, 600, 1);
        cx = getWidth()/2; cy = getHeight()/2;
        this.gameType = gameType;
    }
    public void act(){
        if(counting&&timer.millisElapsed()>2000){
            endGame();
        }
        if(ended&&timer.millisElapsed()>1000){
            newGame();
        }
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
        
        addObject(redTankSprite, 50, cy-100);
        addObject(greenTankSprite, getWidth()-50, cy-100);
        addObject(redScoreCounter, 50, cy+100);
        addObject(greenScoreCounter, getWidth()-50, cy+100);
        redTankSprite.turn(90);
        redTankSprite.getImage().scale(45, 30);
        greenTankSprite.turn(90);
        greenTankSprite.getImage().scale(45, 30);
    }
    public void startCounting(){
        if(ended) return;
        counting = true;
        timer.mark();
    }
    public void endGame(){
        if(!ended){
            ended = true;
            if(!redDied){ 
                redScoreCounter.add(1);
                addObject(new Explosion(), redScoreCounter.getX(), redScoreCounter.getY());
            }
              if(!greenDied){ 
                greenScoreCounter.add(1);
                addObject(new Explosion(), greenScoreCounter.getX(), greenScoreCounter.getY());
            }
            timer.mark();
        }
    }
}
