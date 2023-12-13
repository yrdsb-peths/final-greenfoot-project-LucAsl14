import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Game extends World
{
    String gameType;
    public Game(String gameType)
    {    
        super(1200, 600, 1);
        this.gameType = gameType;
    }
    public Game(int width, int height, int cellSize){
        super(width, height, cellSize);
    }
    public void act(){
        Greenfoot.setWorld(new Maze1(gameType));
    }
}
