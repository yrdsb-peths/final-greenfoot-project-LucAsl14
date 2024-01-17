import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;
/**
 * the death ray is a homing ray that can pierce through walls
 */
public class DeathRay extends Bullet
{
    double homedX, homedY;
    // when isFunny is true, the ray becomes a polygonal ray
    public static boolean isFunny = false;
    public static boolean toggleFunny(){
        isFunny = !isFunny;
        if(isFunny){
            homingStrength = 60;
            homingDelay = 5;
        } else {
            homingStrength = 1;
            homingDelay = 1;
        }
        return isFunny;
    }
    static int homingStrength = 1; // 60 would be funny
    static int homingDelay = 1; // 5 would be funny
    final double velocity = 25;
    int homingCount = 0;
    Tank target;
    boolean needNewTarget = false;
    /**
     * creates a new death ray bullet that has a large lifespan
     */
    public DeathRay(Tank owner, double dir){
        super(owner, dir);    
        lifeSpan*=100;
    }
    /**
     * when added to world, chooses a target to home onto that is not the owner
     */
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
    private void updateHoming(SmoothMover owner){
        homedX = target.getExactX();
        homedY = target.getExactY();  
    }
    public void act()
    {
        Game world = (Game)getWorld();
        // when lifespan runs out (should never happen)
        if(lifeSpan--==0){
            world.removeObject(this);
            return;
        }
        turnTowards(homedX, homedY);
        move(velocity);
        checkKill();
        // if the first target has been killed, consider choosing a new target
        if(needNewTarget){
            needNewTarget = false;
            List<Tank> targets = getObjectsInRange(3000, Tank.class);
            if(targets.size()>=2){
                targets.remove(owner);
                target = targets.get(0);
                updateHoming(target);
            }
        }
        // if checkEdge returns true, it means that this object has been deleted
        if(checkEdge()) return;       
    }
    private boolean checkEdge(){
        if(isAtEdge()){
             getWorld().removeObject(this);
             return true;
        } 
        return false;
    }
    /**
     * override for actor's turnTowards, to direct it towards my special function
     */
    public void turnTowards(int x, int y){
        turnTowards((double)x, (double)y);
    }
    /**
     * instead of turning instantly like the actor class's turnTowards,
     * I have it turning slowly
     */
    private void turnTowards(double x, double y){
        double dx = getExactX()-x;
        double dy = getExactY()-y;
        Game world = (Game) getWorld();
        // turns the aimed location into an absolute angle heading
        int intendedAngle = (int)(Math.toDegrees(Math.atan(dy/dx)));
        if(dx<0) intendedAngle = 180+intendedAngle;
        if(dx>=0&&dy<0) intendedAngle = 360+intendedAngle;
        // calls the homeToDirection with the newfound angle
        homeToDirection(intendedAngle, homingStrength);
    }
    private void homeToDirection(int angle, int speed){
        if(getRotation()!=angle){
            int turnSpeed;
            int rotationDiff = getRotation()-angle;
            // if it would be closer to turn left, then do so. Vice versa.
            if(rotationDiff>180
             ||(rotationDiff<0 && rotationDiff>-180)){
                 turnSpeed = -speed;
            } else {
                 turnSpeed = speed;
            }
            // if i want to delay the homing for some reason 
            if(homingCount==homingDelay){
                turn(turnSpeed);
                homingCount=0;
            }
            homingCount++;
        }        
    }
    /**
     * Override of Bullet's checkKill to make this look for new target
     */
    public void checkKill(){
        if(isTouching(Tank.class)){
            Tank dyingTank = (Tank) getOneIntersectingObject(Tank.class);
            needNewTarget = true;
            dyingTank.gameOver();
            lifeSpan = 0;
        }
    }
}
