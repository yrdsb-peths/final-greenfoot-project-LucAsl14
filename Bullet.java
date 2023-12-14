import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This is shot by tanks, can destroy tanks
 */
public class Bullet extends SmoothMover
{
    Tank owner;
    int velocity = 10;
    double vx, vy;
    int lifeSpan = 1000;
    /**
     * Creates a new bullet with an owner and a direction
     */
    public Bullet(Tank own, double dir){
        owner = own;
        vx = velocity*Math.cos(Math.toRadians(dir));
        vy = velocity*Math.sin(Math.toRadians(dir));
        getImage().scale(10, 10);
    }
    protected void addedToWorld(World world){
        // makes the bullet leave the barrel of the gun instead of exploding inside
        while(intersects(owner)){
            move();
        }
    }
    public void act()
    {
        Game world = (Game) getWorld();
        // when bullet runs out of lifespan
        if(lifeSpan--==0){
            world.removeObject(this);
            owner.bulletsShot--;
            return;
        }
        
        move();
        checkBounce();
        checkKill();
    }
    
    public void move(){
        setLocation(getX()+vx, getY()+vy);
    }
    
    public void checkBounce(){
        // world wall bounces
        if(getY()<=0||getY()>=getWorld().getHeight()-1)
            vy*=-1;
        if(getX()<=0||getX()>=getWorld().getWidth()-1)
            vx*=-1;
            
            
    }
    
    public void checkKill(){
        if(isTouching(Tank.class)){
            Actor dying = getOneIntersectingObject(Tank.class);
            Tank dyingTank;
            if(dying instanceof Tank){
                dyingTank = (Tank) dying;
                dyingTank.gameOver();
            }
        }
    }
}
