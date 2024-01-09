import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Trap here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Trap extends SmoothMover
{
    // when funnyTraps is true, traps explode indefinitely once triggered
    public static boolean funnyTraps = false;
    public static boolean toggleFunny(){
        funnyTraps = !funnyTraps;
        return funnyTraps;
    }
    
    boolean isAnimating = true;
    boolean isHidden = true;
    boolean isExploding = false;
    int transparency = 255;
    double dx, dy;
    final int distanceBehind = 50;
    SimpleTimer timer;
    GreenfootSound beep = new GreenfootSound("beep.mp3");
    public Trap(double dir){
        getImage().scale(30, 30);
        dx = distanceBehind*Math.cos(Math.toRadians(dir));
        dy = distanceBehind*Math.sin(Math.toRadians(dir));
    }
    public void addedToWorld(World world){
        setLocation(getX()-dx, getY()-dy);
        beep.play();
    }
    public void act(){
        if(isAnimating){
            getImage().setTransparency(transparency-=4);
            if(transparency<=4){
                getImage().setTransparency(0);
                isAnimating = false;
            }
        }
        checkCollision();
        checkExplode();
    }
    private void checkCollision(){
        if(isTouching(Tank.class)){
            isHidden = false;
            if(getImage().getTransparency()!=255) beep.play();
            getImage().setTransparency(255);
        } else {
            if(!isHidden){
                isExploding = true;
            }
        }
    }
    private void checkExplode(){
        Game world = (Game) getWorld();
        if(isExploding){
            if(timer==null) timer = new SimpleTimer();
            if(timer.millisElapsed()<250) return;
            if(funnyTraps&&timer.millisElapsed()<500) return;
            if(funnyTraps) world.addObject(new Bomb(true, 0.1, true), getX(), getY());
            else world.addObject(new Bomb(true, 4, false), getX(), getY());
            if(!funnyTraps) world.removeObject(this);
        }
    }
}
