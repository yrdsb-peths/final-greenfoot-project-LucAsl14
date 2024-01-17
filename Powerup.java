import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;
/**
 * Responsible for the creation of powerups
 */
public class Powerup extends Actor
{
    GreenfootImage[] powerups = {
        new GreenfootImage("gatling_gun.png"),
        new GreenfootImage("remote_control.png"),
        new GreenfootImage("frag_bomb.png"),
        new GreenfootImage("booby_trap.png"),
        new GreenfootImage("death_ray.png"),
        new GreenfootImage("wall_delete.png")
        };
    // You can edit the chances array to make certain powerups less likely to appear
    //          power indices = { gatling, remote, frag, trap,  ray, drill };
    final  int[]      chances = {     100,    100,  100,  100,  100,   100 };
    static boolean[] isActive = {    true,   true, true, true, true,  true };
    /**
     * 0 - gatling gun
     * 1 - remote control
     * 2 - frag bomb
     * 3 - booby trap
     * 4 - death ray
     * 5 - wall deleting bullet (drill)
     */
    int powerIndex;
    /**
     * Creates a new powerup that is random.
     */
    public Powerup(){
        Random rand = new Random();
        int roll = rand.nextInt(100)+1;
        powerIndex = rand.nextInt(powerups.length);
        // if the roll is greater than the probability of getting that powerup,
        // or if the powerup is inactive,
        // reroll to pick a new powerup
        while(roll > chances[powerIndex] || !isActive[powerIndex]){
            roll = rand.nextInt(100);
            powerIndex = rand.nextInt(powerups.length);
        }
        setImage(powerups[powerIndex]);
        getImage().scale(50, 50);
    }
    /**
     * Creates a new powerup that is predefined.
     */
    public Powerup(int index){
        powerIndex = index;
        setImage(powerups[powerIndex]);
        getImage().scale(50, 50);
    }
    public void addedToWorld(World world){
        // to make sure there is only 1 powerup on a tile at a time
        if(isTouching(Powerup.class)){
            getWorld().removeObject(this);
            return;
        }
        new GreenfootSound("metallic-ting.mp3").play();
    }
    /**
     * Called from a button is the "PowerupSettings" world, this toggles 
     * whether a certain powerup is active
     */
    public static boolean togglePowerup(int index){
        isActive[index] = !isActive[index];
        return isActive[index];
    }
    /**
     * used to translate the power index into a string with the powerup name
     */
    public String toString(){
        if(powerIndex == 0) return "gatling";
        if(powerIndex == 1) return "remote";
        if(powerIndex == 2) return "bomb";
        if(powerIndex == 3) return "trap";
        if(powerIndex == 4) return "ray";
        if(powerIndex == 5) return "drill";
        return "";
    }
}
