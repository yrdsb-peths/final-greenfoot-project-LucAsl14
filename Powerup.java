import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;
/**
 * Write a description of class Powerup here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
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
    //          power indices = { gatling, remote, frag, trap,  ray, drill };
    int[]             chances = {     100,    100,  100,  100,  100,   100 };
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
    public Powerup(){
        Random rand = new Random();
        int roll = rand.nextInt(100)+1;
        powerIndex = rand.nextInt(powerups.length);
        while(roll > chances[powerIndex]){
            roll = rand.nextInt(100);
            powerIndex = rand.nextInt(powerups.length);
        }
        setImage(powerups[powerIndex]);
        getImage().scale(50, 50);
    }
    // for pre-defined powerups
    public Powerup(int index){
        powerIndex = index;
        setImage(powerups[powerIndex]);
        getImage().scale(50, 50);
    }
    public void addedToWorld(World world){
        if(isTouching(Powerup.class)){
            getWorld().removeObject(this);
            return;
        }
        new GreenfootSound("metallic-ting.mp3").play();
    }
    public static boolean togglePowerup(int index){
        isActive[index] = !isActive[index];
        return isActive[index];
    }
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
