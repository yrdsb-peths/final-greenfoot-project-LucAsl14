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
        new GreenfootImage("remote_control.png")
        };
    /**
     * 0 - gatling gun
     * 1 - remove control
     */
    int powerIndex;
    public Powerup(){
        Random rand = new Random();
        powerIndex = rand.nextInt(2);
        setImage(powerups[powerIndex]);
        getImage().scale(50, 50);
    }
    public String toString(){
        if(powerIndex == 0) return "gatling";
        if(powerIndex == 1) return "remote";
        return "";
    }
}
