/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kazuyuki.T
 */
public class Player extends Unit implements Cloneable {
    private int maxSatiety; // 満腹度
    private int satiety; // 満腹度
    private int level; // レベル
    private int exp; // 経験値
    private int sumExp; // 総取得経験値
    private Inventory inventory; // インベントリ
    
    public Player(){
        super();
        setObjName("player");
        setObjNum(0);
        maxSatiety = 0;
        satiety = maxSatiety;
        level = 0;
        exp = 0;
        sumExp = 0;
    }
    
    public void init(){
        
    }
    
    public Player clone(){
        Player player = new Player();
        try{
            player = (Player)super.clone();
            
        } catch(Exception e) {
            e.printStackTrace();
        }
        return player;
    }
    
    public void activate(int x, int y, int maxHp, int hp, int attack, int maxSatiety, int satiety, int level, int exp, int sumExp){
        setActive(true);
        setPosX(x);
        setPosY(y);
        
        setActionFlag(false);
        setMaxHp(maxHp);
        setHp(hp);
        setAttack(attack);
        
        this.maxSatiety = maxSatiety;
        this.satiety = satiety;
        this.level = level;
        this.exp = exp;
        this.sumExp = sumExp;
    }
    
    public int getMaxSatiety() { return maxSatiety; };
    public void setMaxSatiety(int maxSatiety) { this.maxSatiety = maxSatiety; };
    
    public int getSatiety() { return satiety; };
    public void setSatiety(int satiety) { this.satiety = satiety; };
    
    public int getLevel() { return level; };
    public void setLevel(int level) { this.level = level; };
    
    public int getExp() { return exp; };
    public void setExp(int exp) { this.exp = exp; };
    
    public int getSumExp() { return sumExp; };
    public void setSumExp(int sumExp) { this.sumExp = sumExp; };
}
