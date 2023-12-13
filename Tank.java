import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The main actor of the game, the tank is controlled by the player
 */
public class Tank extends SmoothMover
{
    String color;
    boolean hasShot = false;
    int bulletsShot = 0, maxBullets = 5;
    int moveSpeed = 4, turnSpeed = 3;
    /** creates a tank with color "color" */
    public Tank(String color){
        this.color = color;
        setImage(color+"Tank_base.png");
        getImage().scale(45, 30);
    }
    
    public void act(){
        checkKeys();
    }
    /** checks whether keys have been pressed for either tank */
    private void checkKeys(){
        Game world = (Game)getWorld();
        if(color=="red"){
            if(Greenfoot.isKeyDown("w")){
                move(moveSpeed);
                if(
            }
            if(Greenfoot.isKeyDown("a")){
                turn(-turnSpeed);
            }
            if(Greenfoot.isKeyDown("s")){
                move(-moveSpeed);
            }
            if(Greenfoot.isKeyDown("d")){
                turn(turnSpeed);
            }
            if(Greenfoot.isKeyDown("q")&&!hasShot&&!checkExceed()){
                world.addObject(new Bullet(this, getRotation()), getX(), getY());
                hasShot = true;
            } else {
                hasShot = false;
            }
        }
        if(color=="green"){
            if(Greenfoot.isKeyDown("up"))
                move(moveSpeed);
            if(Greenfoot.isKeyDown("left"))
                turn(-turnSpeed);
            if(Greenfoot.isKeyDown("down"))
                move(-moveSpeed);
            if(Greenfoot.isKeyDown("right"))
                turn(turnSpeed); 
            if(Greenfoot.isKeyDown("m")&&!hasShot&&!checkExceed()){
                world.addObject(new Bullet(this, getRotation()), getX(), getY());
                hasShot = true;
            } else {
                hasShot = false;
            }
        }
    }
    public boolean checkExceed(){
        return bulletsShot >= maxBullets;
    }
}
