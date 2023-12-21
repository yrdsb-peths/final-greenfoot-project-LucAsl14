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
        new GreenfootImage("booby_trap.png")
        };
    /**
     * 0 - gatling gun
     * 1 - remove control
     * 2 - frag bomb
     * 4 - booby trap
     */
    int powerIndex;
    public Powerup(){
        Random rand = new Random();
        powerIndex = rand.nextInt(4);
        setImage(powerups[powerIndex]);
        getImage().scale(50, 50);
    }
    public String toString(){
        if(powerIndex == 0) return "gatling";
        if(powerIndex == 1) return "remote";
        if(powerIndex == 2) return "bomb";
        if(powerIndex == 3) return "trap";
        return "";
    }
}
