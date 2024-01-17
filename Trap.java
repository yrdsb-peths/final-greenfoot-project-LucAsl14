import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * a trap that can be placed down and become invisible, it explodes when
 * stepped on
 */
public class Trap extends SmoothMover
{
    // when isFunny is true, traps explode indefinitely once triggered
    public static boolean isFunny = false;
    public static boolean toggleFunny(){
        isFunny = !isFunny;
        return isFunny;
    }
    
    boolean isAnimating = true;
    boolean isHidden = true;
    boolean isExploding = false;
    int transparency = 255;
    double dx, dy;
    final int distanceBehind = 50;
    SimpleTimer timer;
    GreenfootSound beep = new GreenfootSound("beep.mp3");
    /**
     * constructor
     */
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
        // becomes invisible
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
        // explodes if touching a tank
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
    /**
     * explosion logics
     */
    private void checkExplode(){
        Game world = (Game) getWorld();
        if(isExploding){
            if(timer==null) timer = new SimpleTimer();
            if(timer.millisElapsed()<250) return;
            if(isFunny&&timer.millisElapsed()<500) return;
            if(isFunny) world.addObject(new Bomb(true, 0.1, true), getX(), getY());
            else world.addObject(new Bomb(true, 4, false), getX(), getY());
            if(!isFunny) world.removeObject(this);
            if(world.gameType == "singleplayer") world.removeObject(this);
        }
    }
}
