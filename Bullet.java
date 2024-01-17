import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This is shot by tanks, can destroy tanks
 */
public class Bullet extends SmoothMover
{
    Tank owner;
    final double velocity = 4;
    double vx, vy;
    boolean isSmall = false;
    int lifeSpan = 800;
    GreenfootSound bounce = new GreenfootSound("ping_pong_ball.mp3");
    /**
     * Creates a new bullet with an owner and a direction
     */
    public Bullet(Tank own, double dir){
        owner = own;
        vx = velocity*Math.cos(Math.toRadians(dir));
        vy = velocity*Math.sin(Math.toRadians(dir));
        setRotation((int)dir);
        getImage().scale(10, 10);
    }
    /**
     * Creates a new small bullet with owner and direction
     */
    public Bullet(Tank own, double dir, boolean isSmall){
        this(own, dir);
        if(isSmall){
            getImage().scale(5, 5);
            lifeSpan /= 4;  
            isSmall = true;
        }
    }
    protected void addedToWorld(World world){
        // makes the bullet leave the barrel of the gun instead of exploding inside
        while(owner!=null&&intersects(owner)&&!isTouching(Wall.class)){
            move();
        }
    }
    public void act()
    {
        Game world = (Game) getWorld();
        // when bullet runs out of lifespan
        if(lifeSpan--==0){
            world.removeObject(this);
            if(owner!=null&&!isSmall) owner.bulletsShot--;
            return;
        }
        
        move();
        checkKill();
        checkBounce();
    }
    
    public void move(){
        setLocation(getExactX()+vx, getExactY()+vy);
    }
    /**
     * Checks for wall bounces
     */
    public void checkBounce(){
        if(isTouching(Wall.class)){
            Wall wall = (Wall) getOneIntersectingObject(Wall.class);
            bounce.play();
            if(wall instanceof LeftVerticalWall){
                vx=-Math.abs(vx);
            }
            if(wall instanceof RightVerticalWall){
                vx=Math.abs(vx);
            }
            if(wall instanceof TopHorizontalWall){
                vy=-Math.abs(vy);
            }
            if(wall instanceof BottomHorizontalWall){
                vy=Math.abs(vy); 
            }
        }
            
    }
    
    /**
     * Checks whether bullet collided with tank
     */
    public void checkKill(){
        if(isTouching(Tank.class)){
            Tank dyingTank = (Tank) getOneIntersectingObject(Tank.class);
            dyingTank.gameOver();
            lifeSpan = 0;
        }
    }
}
