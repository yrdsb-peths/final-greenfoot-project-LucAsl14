import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class ControlledBullet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ControlledBullet extends Bullet
{
    final int turnSpeed = 5;
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
        world.addObject(new Explosion(9), getX(), getY());
        move(velocity);
        checkKill();
        checkBounce();
    }
    public void checkBounce(){
        // world wall bounces
        if(getY()<=0||getY()>=getWorld().getHeight()-1){
            setRotation(180-getRotation());
        }
        if(getX()<=0||getX()>=getWorld().getWidth()-1){
            setRotation(180-getRotation());
        }
        if(isTouching(Wall.class)){
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
    public void checkKeys(){
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
