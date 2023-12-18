import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The main actor of the game, the tank is controlled by the player
 */
public class Tank extends SmoothMover
{
    String color;
    boolean destroyed = false;
    boolean hasShot = false;
    int bulletsShot = 0, maxBullets = 10;
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
                    move(-moveSpeed+1);
                }
            }
            if(Greenfoot.isKeyDown("a")){
                turn(-turnSpeed);
            }
            if(Greenfoot.isKeyDown("s")){
                move(-moveSpeed);
                if(isTouching(null)||isAtEdge()){
                    move(moveSpeed-1);
                }
            }
            if(Greenfoot.isKeyDown("d")){
                turn(turnSpeed);
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
                    move(-moveSpeed+1);
                }
            }
            if(Greenfoot.isKeyDown("left")){
                turn(-turnSpeed);
            }
            if(Greenfoot.isKeyDown("down")){
                move(-moveSpeed);
                if(isTouching(null)||isAtEdge()){
                    move(moveSpeed-1);
                }
            }
            if(Greenfoot.isKeyDown("right")){
                turn(turnSpeed);
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
        
        if(isTouching(RightVerticalWall.class)){
            setLocation(getX()+moveSpeed, getY());
        }
        if(isTouching(LeftVerticalWall.class)){
            setLocation(getX()-moveSpeed, getY());
        }
        if(isTouching(TopHorizontalWall.class)){
            setLocation(getX(), getY()-moveSpeed);
        }
        if(isTouching(BottomHorizontalWall.class)){
            setLocation(getX(), getY()+moveSpeed);
        }
    }
    public boolean checkExceed(){
        return bulletsShot >= maxBullets;
    }
    public void gameOver(){
        Game world = (Game) getWorld();
        world.addObject(new Explosion(), getX(), getY());
        destroyed = true;
        if(color == "red"){
            world.redDied = true;
            world.startCounting();
        }
        if(color == "green"){
            world.greenDied = true;
            world.startCounting();
        }
        getWorld().removeObject(this);
    }
}
