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
    boolean uncontrollable = false;
    boolean bombShot = false;
    boolean startedRay = false;
    int bulletsShot = 0; 
    int trapsFired = 0;
    final int maxBullets = 5;
    final int moveSpeed = 3, turnSpeed = 4;
    final int gatlingSpread = 20;
    final int maxTrap = 3;
    SimpleTimer timer = new SimpleTimer();
    SimpleTimer chargingRay; 
    Random rand = new Random();
    GreenfootSound charging = new GreenfootSound("charging.mp3");
    GreenfootSound beam = new GreenfootSound("heavy-beam-weapon.mp3");
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
            if(Greenfoot.isKeyDown("w")&&!uncontrollable){
                move(moveSpeed);
                if(isTouching(Wall.class)){
                    move(-moveSpeed+1);
                }
            }
            if(Greenfoot.isKeyDown("a")&&!uncontrollable){
                turn(-turnSpeed);
            }
            if(Greenfoot.isKeyDown("s")&&!uncontrollable){
                move(-moveSpeed);
                if(isTouching(Wall.class)){
                    move(moveSpeed-1);
                }
            }
            if(Greenfoot.isKeyDown("d")&&!uncontrollable){
                turn(turnSpeed);
            }
        }
        if(color=="green"){
            if(Greenfoot.isKeyDown("up")&&!uncontrollable){
                move(moveSpeed);
                if(isTouching(Wall.class)){
                    move(-moveSpeed+1);
                }
            }
            if(Greenfoot.isKeyDown("left")&&!uncontrollable){
                turn(-turnSpeed);
            }
            if(Greenfoot.isKeyDown("down")&&!uncontrollable){
                move(-moveSpeed);
                if(isTouching(Wall.class)){
                    move(moveSpeed-1);
                }
            }
            if(Greenfoot.isKeyDown("right")&&!uncontrollable){
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
            if(Greenfoot.isKeyDown("q")&&!uncontrollable&&!bombShot){
                if(currentPowerup=="none"&&!hasShot&&!checkExceed()){
                    world.addObject(new Bullet(this, getRotation()), getX(), getY());
                    hasShot = true;
                    bulletsShot++;
                }
                else if(currentPowerup=="gatling"){
                    if(timer.millisElapsed()<1000){
                        startedGatling = true;
                        charging.play();
                    } else if(timer.millisElapsed()>2000){
                        currentPowerup = "none";
                        charging.stop();
                    } else {
                        int adj = rand.nextInt(gatlingSpread)-gatlingSpread/2;
                        world.addObject(new Bullet(this, getRotation()+adj, true), getX(), getY());
                        hasShot = true;
                    }
                }
                else if(currentPowerup=="remote"){
                    uncontrollable = true;
                    world.addObject(new ControlledBullet(this), getX(), getY());
                    hasShot = true;
                }
                else if(currentPowerup=="bomb"){
                    bombShot = true;
                    world.addObject(new Bomb(this, getRotation()), getX(), getY());
                    hasShot = true;
                }
                else if(currentPowerup=="trap"&&!hasShot){
                    world.addObject(new Trap(getRotation()), getX(), getY());
                    trapsFired++;
                    if(trapsFired>=3){
                        currentPowerup = "none";
                        trapsFired = 0;
                    }
                    hasShot = true;
                } else if(currentPowerup=="ray"&&!startedRay){
                    uncontrollable = true;
                    startedRay = true;
                    chargingRay = new SimpleTimer();
                }
            } else {
                if(!Greenfoot.isKeyDown("q")) hasShot = false;
                timer.mark();
                if(startedGatling){
                    startedGatling = false;
                    currentPowerup = "none";
                    charging.stop();
                }
            }
        }
        if(color == "green"){
            if(Greenfoot.isKeyDown("m")&&!uncontrollable&&!bombShot){
                if(currentPowerup=="none"&&!hasShot&&!checkExceed()){
                    world.addObject(new Bullet(this, getRotation()), getX(), getY());
                    hasShot = true;
                    bulletsShot++;
                }
                else if(currentPowerup=="gatling"){
                    if(timer.millisElapsed()<500){
                        startedGatling = true;
                        charging.play();
                    } else if(timer.millisElapsed()>2000){
                        currentPowerup = "none";
                        charging.stop();
                    } else {
                        int adj = rand.nextInt(gatlingSpread)-gatlingSpread/2;
                        world.addObject(new Bullet(this, getRotation()+adj, true), getX(), getY());
                        hasShot = true;
                    }
                }
                else if(currentPowerup=="remote"){
                    uncontrollable = true;
                    world.addObject(new ControlledBullet(this), getX(), getY());
                    hasShot = true;
                }
                else if(currentPowerup=="bomb"){
                    bombShot = true;
                    world.addObject(new Bomb(this, getRotation()), getX(), getY());
                    hasShot = true;
                }
                else if(currentPowerup=="trap"&&!hasShot){
                    world.addObject(new Trap(getRotation()), getX(), getY());
                    trapsFired++;
                    if(trapsFired>=3){
                        currentPowerup = "none";
                        trapsFired = 0;
                    }
                    hasShot = true;
                } else if(currentPowerup=="ray"&&!startedRay){
                    uncontrollable = true;
                    startedRay = true;
                    chargingRay = new SimpleTimer();
                }
            } else {
                if(!Greenfoot.isKeyDown("m")) hasShot = false;
                timer.mark();
                if(startedGatling){
                    startedGatling = false;
                    currentPowerup = "none";
                    charging.stop();
                }
            }  
        }
        
        if(startedRay){
            if(chargingRay.millisElapsed()<2000){
                charging.play();
            }
            if(chargingRay.millisElapsed()>2000){
                charging.stop();
                beam.play();
                world.addObject(new DeathRay(this, getRotation()), getX(), getY());
                hasShot = true;
            }
            if(chargingRay.millisElapsed()>4000){
                beam.stop();
                uncontrollable = false;
                startedRay = false;
                currentPowerup = "none";
            }
        }
    }
    private boolean checkExceed(){
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
        if(color == "blue"){
            world.redScoreCounter.add(1);
            world.addObject(new Explosion(), world.redScoreCounter.getX(), world.redScoreCounter.getY());
            Random rand = new Random();
            int x = rand.nextInt(13);
            int y = rand.nextInt(8);
            setLocation(x*75+150, y*75+37);
            setRotation(rand.nextInt(360));
            return;
        }
        getWorld().removeObject(this);
    }
    public String getColor(){
        return color;
    }
    public void remoteExpire(){
        uncontrollable = false;
        currentPowerup = "none";
    }
    public void bombExpire(){
        bombShot = false;
        currentPowerup = "none";
        hasShot = true;
    }
    public boolean hasShot(){
        return hasShot;
    }
}
