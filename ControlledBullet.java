import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A bullet that is controlled by a player
 */
public class ControlledBullet extends Bullet
{
    final int turnSpeed = 5;
    /**
     * makes a new controlled bullet
     */
    public ControlledBullet(Tank owner){
        super(owner, owner.getRotation());
        getImage().scale(15,15);
    }
    public void act(){
        Game world = (Game) getWorld();
        // when bullet runs out of lifespan
        if(lifeSpan--==0){
            owner.remoteExpire();
            world.removeObject(this);
            return;
        }
        checkKeys();
        // adds a trail of explosion for cosmetics
        world.addObject(new Explosion(9), getX(), getY());
        move(velocity);
        checkKill();
        checkBounce();
    }
    /**
     * special bouncing that comes with the fact that vx and vy were not used
     */
    protected void checkBounce(){
        if(isTouching(Wall.class)){
            bounce.play();
            Wall wall = (Wall) getOneIntersectingObject(Wall.class);
            if(wall instanceof LeftVerticalWall){
                setRotation(180-getRotation());
                setLocation(getX()-velocity, getY());
            }
            if(wall instanceof RightVerticalWall){
                setRotation(180-getRotation());
                setLocation(getX()+velocity, getY());
            }
            if(wall instanceof TopHorizontalWall){
                setRotation(360-getRotation());
                setLocation(getX(), getY()-velocity);
            }
            if(wall instanceof BottomHorizontalWall){
                setRotation(360-getRotation());
                setLocation(getX(), getY()+velocity);
            }
        }
    }
    /**
     * checks for key presses from its owner
     */
    private void checkKeys(){
        if(owner.getColor()=="red"){
            if(Greenfoot.isKeyDown("a")){
                turn(-turnSpeed);
            }
            if(Greenfoot.isKeyDown("d")){
                turn(turnSpeed);
            }
        }
        if(owner.getColor()=="green"){
            if(Greenfoot.isKeyDown("left")){
                turn(-turnSpeed);
            }
            if(Greenfoot.isKeyDown("right")){
                turn(turnSpeed);
            }
        }
    }
}
