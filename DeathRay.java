import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * Write a description of class DeathRay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DeathRay extends Bullet
{
    double homedX, homedY;
    final int homingStrength = 1;
    final int homingDelay = 1;
    final double velocity = 25;
    int homingCount = 0;
    Tank target;
    boolean needNewTarget = false;
    public DeathRay(Tank owner, double dir){
        super(owner, dir);    
        lifeSpan*=100;
    }
    public void addedToWorld(World world){
        while(owner!=null&&intersects(owner)){
            move();
            if(isAtEdge()){
                world.removeObject(this);
                return;
            }
        }
        List<Tank> targets = getObjectsInRange(3000, Tank.class);
        if(targets.size()>=2){
            targets.remove(owner);
            target = targets.get(0);
            updateHoming(target);
        }
    }
    public void updateHoming(SmoothMover owner){
        homedX = target.getExactX();
        homedY = target.getExactY();  
    }
    /**
     * Act - do whatever the HomingSpit wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        Game world = (Game)getWorld();
        if(lifeSpan--==0){
            world.removeObject(this);
            return;
        }
        turnTowards(homedX, homedY);
        move(velocity);
        checkKill();
        if(needNewTarget){
            needNewTarget = false;
            List<Tank> targets = getObjectsInRange(3000, Tank.class);
            if(targets.size()>=2){
                targets.remove(owner);
                target = targets.get(0);
                updateHoming(target);
            }
        }
        if(checkEdge()) return;       
    }
    private boolean checkEdge(){
        if(isAtEdge()){
             getWorld().removeObject(this);
             return true;
        } 
        return false;
    }
    public void turnTowards(int x, int y){
        turnTowards((double)x, (double)y);
    }
    public void turnTowards(double x, double y){
        double dx = getExactX()-x;
        double dy = getExactY()-y;
        Game world = (Game) getWorld();
        int intendedAngle = (int)(Math.toDegrees(Math.atan(dy/dx)));
        if(dx<0) intendedAngle = 180+intendedAngle;
        if(dx>=0&&dy<0) intendedAngle = 360+intendedAngle;
        homeToDirection(intendedAngle, homingStrength);
    }
    private void homeToDirection(int angle, int speed){
        if(getRotation()!=angle){
            int turnSpeed;
            int rotationDiff = getRotation()-angle;
            if(rotationDiff>180
             ||(rotationDiff<0 && rotationDiff>-180)){
                 turnSpeed = -speed;
             } else {
                 turnSpeed = speed;
             }
            if(homingCount==homingDelay){
                turn(turnSpeed);
                homingCount=0;
            }
            homingCount++;
        }        
    }
    /**
     * Override of Bullet's checkKill
     */
    public void checkKill(){
        if(isTouching(Tank.class)){
            Actor dying = getOneIntersectingObject(Tank.class);
            Tank dyingTank;
            if(dying instanceof Tank){
                needNewTarget = true;
                dyingTank = (Tank) dying;
                dyingTank.gameOver();
                lifeSpan = 0;
            }
        }
    }
}
