/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kazuyuki.T
 */
public abstract class Unit extends GameObject implements Cloneable{
    private boolean actionFlag; // ターン中のアクションの有無
    private int speed; // 速さ
    private int maxHp; // 体力,0になると消滅
    private int hp;
    private int attack; // 攻撃力
    private int dir; // 向いている方向
    // 7 8 9
    // 4 5 6
    // 1 2 3
    // 0は何らかの処理ミス
    
    public Unit(){
        super();
        actionFlag = false;
        speed = 1;
        maxHp = 0;
        hp = maxHp;
        attack = 0;
        dir = 0;
    }
    
    public Unit clone(){
        Unit unit = null;
        try{
            unit = (Unit)super.clone();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return unit;
    }
    
    public boolean getActionFlag() { return actionFlag; };
    public void setActionFlag(boolean actionFlag) { this.actionFlag = actionFlag; };
    
    public int getSpeed() { return speed; };
    public void setSpeed(int speed) { this.speed = speed; };
    
    public int getMaxHp() { return maxHp; };
    public void setMaxHp(int maxHp) { this.maxHp = maxHp; };
    
    public int getHp() { return hp; };
    public void setHp(int hp) { this.hp = hp; };
    
    public int getAttack() { return attack; };
    public void setAttack(int attack) { this.attack = attack; };

    public int getDir() { return dir; };
    public void setDir(int dir) { this.dir = dir; };    
}
