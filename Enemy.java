/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kazuyuki.T
 */
public class Enemy extends Unit implements Cloneable {
    private int spoint; // 撃破時に得られる経験値
    
    public Enemy(){
        super();
        setObjName("enemy");
        setObjNum(1);
        spoint = 0;
    }
    
    public void init(){
        
    }
    
    public Enemy clone(){
        Enemy enemy = new Enemy();
        try{
            enemy = (Enemy)super.clone();
            
        } catch(Exception e) {
            e.printStackTrace();
        }
        return enemy;    
    }
    
    public void activate(int x, int y, int maxHp, int hp, int attack, int spoint){
        setActive(true);
        setPosX(x);
        setPosY(y);
        
        setActionFlag(false);
        setMaxHp(maxHp);
        setHp(hp);
        setAttack(attack);
        
        this.spoint = spoint;
    }
    
    public int getSpoint() { return spoint; };
    public void setSpoint(int spoint) { this.spoint = spoint; };
}
