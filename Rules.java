import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Rules extends World
{
    int cx, cy;
    public Rules()
    {    
        super(1200, 600, 1);
        cx = getWidth()/2; cy = getHeight()/2;
        setBackground("tankBackground.jpg");
        showRules();
    }
    
    public void showRules(){
        Label[] instr = new Label[6];
        instr[0] = new Label("For 1 player, the goal is to shoot as many targets as possible", 50);
        instr[1] = new Label("For 2 players, the goal is to destroy the other tank", 50);
        instr[2] = new Label("powerups are for you to figure out :)", 50);
        instr[3] = new Label("Player 1 is red and moves with WASD and shoots with Q", 50);
        instr[4] = new Label("Player 2 is green and moves with ARROWS and shoots with M", 50);
        instr[5] = new Label("(click anywhere to go back)", 50);
        
        for(int i=0; i<6; i++){
            addObject(instr[i], cx, 50+i*80);
        }
    }
    
    public void act(){
        if(Greenfoot.mouseClicked(null)){
            Greenfoot.setWorld(new TitleScreen());
        }
    }
}
