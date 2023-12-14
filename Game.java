import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Game extends World
{
    String gameType;
    boolean createdGame = false;
    public Game(String gameType){    
        super(1200, 600, 1);
        this.gameType = gameType;
    }
    public Game(int width, int height, int cellSize, String gameType){
        super(width, height, cellSize);
        this.gameType = gameType;
    }
    // public void act(){
        // if(!createdGame){
            // Greenfoot.setWorld(new Maze1(gameType));
            // createdGame = true;
        // }
    // }
}
