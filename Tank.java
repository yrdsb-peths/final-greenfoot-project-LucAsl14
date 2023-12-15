import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The main actor of the game, the tank is controlled by the player
 */
public class Tank extends SmoothMover
{
    String color;
    boolean destroyed = false;
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
        if(destroyed) return;
        checkKeys();
    }
    /** checks whether keys have been pressed for either tank */
    private void checkKeys(){
        Game world = (Game)getWorld();
        if(color=="red"){
            if(Greenfoot.isKeyDown("w")){
                move(moveSpeed);
                if(isTouching(null)||isAtEdge()){
                    move(-moveSpeed);
                }
            }
            if(Greenfoot.isKeyDown("a")){
                turn(-turnSpeed);
                if(isTouching(null)){
                    turn(moveSpeed);
                }
            }
            if(Greenfoot.isKeyDown("s")){
                move(-moveSpeed);
                if(isTouching(null)||isAtEdge()){
                    move(moveSpeed);
                }
            }
            if(Greenfoot.isKeyDown("d")){
                turn(turnSpeed);
                if(isTouching(null)){
                    turn(-moveSpeed);
                }
            }
            
            // the shooting
            if(Greenfoot.isKeyDown("q")){
                if(!hasShot&&!checkExceed()){
                    world.addObject(new Bullet(this, getRotation()), getX(), getY());
                    hasShot = true;
                    bulletsShot++;
                }
            } else {
                hasShot = false;
            }
        }
        if(color=="green"){
            if(Greenfoot.isKeyDown("up")){
                move(moveSpeed);
                if(isTouching(null)||isAtEdge()){
                    move(-moveSpeed);
                }
            }
            if(Greenfoot.isKeyDown("left")){
                turn(-turnSpeed);
                if(isTouching(null)){
                    turn(moveSpeed);
                }
            }
            if(Greenfoot.isKeyDown("down")){
                move(-moveSpeed);
                if(isTouching(null)||isAtEdge()){
                    move(moveSpeed);
                }
            }
            if(Greenfoot.isKeyDown("right")){
                turn(turnSpeed);
                if(isTouching(null)){
                    turn(-moveSpeed);
                }
            }
            
            // the shooting
            if(Greenfoot.isKeyDown("m")){
                if(!hasShot&&!checkExceed()){
                    world.addObject(new Bullet(this, getRotation()), getX(), getY());
                    hasShot = true;
                    bulletsShot++;
                }
            } else {
                hasShot = false;
            }
        }
    }
    public boolean checkExceed(){
        return bulletsShot >= maxBullets;
    }
    public void gameOver(){
        getWorld().addObject(new Explosion(), getX(), getY());
        destroyed = true;
        getWorld().removeObject(this);
    }
}
