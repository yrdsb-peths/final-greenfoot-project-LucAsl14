import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;
/**
 * This class was originally intended to manage many games, but due to time
 * constraints, only Maze1 is available
 */
public class Game extends World
{
    String gameType;
    boolean redDied = false, greenDied = false; 
    boolean counting = false, ended = false;
    SimpleTimer timer = new SimpleTimer();
    SimpleTimer powerTimer = new SimpleTimer();
    final int cx = getWidth()/2, cy = getHeight()/2;
    static Counter redScoreCounter = new Counter();
    static Counter greenScoreCounter = new Counter();
    Counter timeCounter = new Counter();
    /**
     * creates a new game
     * @param gameType whether the game should be singleplayer or multiplayer
     */
    public Game(String gameType){    
        super(1200, 600, 1);
        this.gameType = gameType;
        powerTimer.mark();
    }
    public void act(){
        // if it's been counting for 3 seconds after a player died, end the game
        if(counting&&timer.millisElapsed()>3000){
            endGame();
            return;
        }
        
        // if the game has been ended for 1 second, create a new game
        if(ended&&timer.millisElapsed()>1000){
            newGame();
            return;
        }
        
        // timeCounter is only used in singleplayer, tracks the time left
        timeCounter.setValue(60-(timer.millisElapsed()/1000));
        if(timeCounter.getValue()<=0&&gameType=="singleplayer"){
            endGame();
            return;
        }
        
        // in multiplayer, powerups spawn every 8 seconds
        // in singleplayer, powerups spawn every 2 seconds
        if(gameType=="multiplayer" && powerTimer.millisElapsed()>8000
         ||gameType=="singleplayer" && powerTimer.millisElapsed()>2000){    
            spawnPowerups();
            powerTimer.mark();
        }
        
        // press ESCAPE to pause
        if(Greenfoot.isKeyDown("escape")){
            Greenfoot.setWorld(new PauseScreen(this));
        }
    }
    
    /**
     * spawns a powerup on a random spot in the map
     */
    private void spawnPowerups(){
        Random rand = new Random();
        int x = rand.nextInt(13);
        int y = rand.nextInt(8);
        addObject(new Powerup(), x*75+150, y*75+37);
    }
    
    /**
     * creates a new game, which comprises of only Maze1 for now
     */
    public void newGame(){
        Greenfoot.setWorld(new Maze1(gameType));
    }
    
    /**
     * creates the 4 borders around a map, to prevent in-game objects from
     * interacting with scoreboards and such
     */
    protected void makeLimits(){
        addObject(new RightVerticalWall(getHeight()), cx-500+12, cy);
        addObject(new LeftVerticalWall(getHeight()), cx+500-12, cy);
        addObject(new BottomHorizontalWall(1000-24), cx, 0);
        addObject(new TopHorizontalWall(1000-24), cx, getHeight());
    }
    
    /**
     * creates the scoreboard for multiplayer games
     */
    protected void makeScores2(){
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
    /**
     * creates the scoreboard for singleplayer games
     */
    protected void makeScores(){
        Label redTankSprite = new Label(new GreenfootImage("redTank_base.png"));
        
        addObject(redTankSprite, 50, cy-100);
        addObject(redScoreCounter, 50, cy+100);
        addObject(timeCounter, getWidth()-50, cy+100);
        
        redTankSprite.turn(90);
        redTankSprite.getImage().scale(45, 30);
    }
    /**
     * called whenever a tank has died, this toggles the counting flag to end
     * the game.
     * This is to make sure that in multiplayer, a player does not die 
     * immediately after killing their opponent.
     */
    public void startCounting(){
        if(ended) return;
        counting = true;
        timer.mark();
    }
    /**
     * called when games are ended, this adds a point to any player that did
     * not die that round in multiplayer, and shows the score screen in
     * singleplayer.
     * Note that if you check the source code, the "problem" Label should
     * normally never show up, so if it does show up, consider sending a bug
     * report :)
     */
    public void endGame(){
        if(!ended){
            ended = true;
            if(gameType=="multiplayer"){
                if(!redDied){ 
                    redScoreCounter.add(1);
                    addObject(new Explosion(), redScoreCounter.getX(), redScoreCounter.getY());
                }
                  if(!greenDied){ 
                    greenScoreCounter.add(1);
                    addObject(new Explosion(), greenScoreCounter.getX(), greenScoreCounter.getY());
                }
            } else if(gameType=="singleplayer"){
                Greenfoot.setWorld(new ScoreScreen(redScoreCounter.getValue()));
            } else {
                Label problem = new Label("something went wrong, please contact dev", 80);
                addObject(problem, cx, cy);
            }
            timer.mark();
        }
    }
    /**
     * resets the score
     */
    public static void resetScores(){
        redScoreCounter.setValue(0);
        greenScoreCounter.setValue(0);
    }
}
