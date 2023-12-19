import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;
/**
 * The main actor of the game, the tank is controlled by the player
 */
public class Tank extends SmoothMover
{
    String color;
    String currentPowerup = "none";
    boolean destroyed = false;
    boolean hasShot = false;
    boolean startedGatling = false;
    int bulletsShot = 0; 
    final int maxBullets = 10;
    final int moveSpeed = 3, turnSpeed = 3;
    final int gatlingSpread = 20;
    SimpleTimer timer = new SimpleTimer();
    Random rand = new Random();
    /** creates a tank with color "color" */
    public Tank(String color){
        this.color = color;
        setImage(color+"Tank_base.png");
        getImage().scale(45, 30);
    }
    
    public void act(){
        if(destroyed) return;
        checkMoves();
        checkShots();
        checkCollisions();
    }
    /** checks whether keys have been pressed for either tank */
    private void checkMoves(){
        Game world = (Game)getWorld();
        if(color=="red"){
            if(Greenfoot.isKeyDown("w")){
                move(moveSpeed);
                if(isTouching(Wall.class)){
                    move(-moveSpeed+1);
                }
            }
            if(Greenfoot.isKeyDown("a")){
                turn(-turnSpeed);
            }
            if(Greenfoot.isKeyDown("s")){
                move(-moveSpeed);
                if(isTouching(Wall.class)){
                    move(moveSpeed-1);
                }
            }
            if(Greenfoot.isKeyDown("d")){
                turn(turnSpeed);
            }
        }
        if(color=="green"){
            if(Greenfoot.isKeyDown("up")){
                move(moveSpeed);
                if(isTouching(Wall.class)){
                    move(-moveSpeed+1);
                }
            }
            if(Greenfoot.isKeyDown("left")){
                turn(-turnSpeed);
            }
            if(Greenfoot.isKeyDown("down")){
                move(-moveSpeed);
                if(isTouching(Wall.class)){
                    move(moveSpeed-1);
                }
            }
            if(Greenfoot.isKeyDown("right")){
                turn(turnSpeed);
            }   
        }
    }
    public void checkCollisions(){
        if(isTouching(RightVerticalWall.class)){
            setLocation(getX()+moveSpeed, getY());
        }
        if(isTouching(LeftVerticalWall.class)){
            setLocation(getX()-moveSpeed, getY());
        }
        if(isTouching(TopHorizontalWall.class)){
            setLocation(getX(), getY()-moveSpeed);
        }
        if(isTouching(BottomHorizontalWall.class)){
            setLocation(getX(), getY()+moveSpeed);
        }
        if(isTouching(Powerup.class)&&currentPowerup=="none"){
            currentPowerup = getOneIntersectingObject(Powerup.class).toString();
            removeTouching(Powerup.class);
        }
    }
    public void checkShots(){
        Game world = (Game) getWorld();
        if(color == "red"){
            if(Greenfoot.isKeyDown("q")){
                if(currentPowerup=="none"&&!hasShot&&!checkExceed()){
                    world.addObject(new Bullet(this, getRotation()), getX(), getY());
                    hasShot = true;
                    bulletsShot++;
                }
                if(currentPowerup=="gatling"){
                    if(timer.millisElapsed()<500){
                        startedGatling = true;
                    } else if(timer.millisElapsed()>2000){
                        currentPowerup = "none";
                    } else {
                        int adj = rand.nextInt(gatlingSpread)-gatlingSpread/2;
                        world.addObject(new Bullet(this, getRotation()+adj, true), getX(), getY());
                        hasShot = true;
                    }
                }
            } else {
                hasShot = false;
                timer.mark();
                if(startedGatling){
                    startedGatling = false;
                    currentPowerup = "none";
                }
            }
        }
        if(color == "green"){
            if(Greenfoot.isKeyDown("m")&&color=="green"){
                if(currentPowerup=="none"&&!hasShot&&!checkExceed()){
                    world.addObject(new Bullet(this, getRotation()), getX(), getY());
                    hasShot = true;
                    bulletsShot++;
                }
                if(currentPowerup=="gatling"){
                    if(timer.millisElapsed()<500){
                        //charging up
                    } else if(timer.millisElapsed()>2000){
                        currentPowerup = "none";
                    } else {
                        int adj = rand.nextInt(gatlingSpread)-gatlingSpread/2;
                        world.addObject(new Bullet(this, getRotation()+adj, true), getX(), getY());
                        hasShot = true;
                    }
                }
            } else {
                hasShot = false;
                timer.mark();
            }  
        }
    }
    public boolean checkExceed(){
        return bulletsShot >= maxBullets;
    }
    public void gameOver(){
        Game world = (Game) getWorld();
        world.addObject(new Explosion(), getX(), getY());
        destroyed = true;
        if(color == "red"){
            world.redDied = true;
            world.startCounting();
        }
        if(color == "green"){
            world.greenDied = true;
            world.startCounting();
        }
        getWorld().removeObject(this);
    }
}
