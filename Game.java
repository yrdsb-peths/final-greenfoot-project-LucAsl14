import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Game extends World
{
    String gameType;
    boolean createdGame = false;
    public Game(String gameType){    
        super(1200, 600, 1);
        this.gameType = gameType;
    }
    public void newGame(){
        Greenfoot.setWorld(new Maze1(gameType));
    }
}
