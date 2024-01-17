import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This world is called when the player presses the rules "button" in the
 * title screen. It displays the rules for the game.
 */
public class Rules extends World
{
    final int cx = getWidth()/2, cy = getHeight()/2;
    /**
     * constructor for Rules
     */
    public Rules()
    {    
        super(1200, 600, 1);
        setBackground("tankBackground.jpg");
        showRules();
    }
    
    private void showRules(){
        Label[] rule = new Label[6];
        rule[0] = new Label("For 1 player, shoot as many targets as possible", 50);
        rule[1] = new Label("For 2 players, destroy the other tank", 50);
        rule[2] = new Label("press ESCAPE to pause game", 50);
        rule[3] = new Label("Player 1 is RED and moves with WASD and shoots with Q", 50);
        rule[4] = new Label("Player 2 is GREEN and moves with ARROWS and shoots with M", 50);
        rule[5] = new Label("(click anywhere to go back)", 50);
        
        for(int i=0; i<6; i++){
            addObject(rule[i], cx, 50+i*80);
        }
    }
    
    public void act(){
        // return to title screen when mouse clicks
        if(Greenfoot.mouseClicked(null)){
            Greenfoot.setWorld(new TitleScreen());
        }
    }
}
