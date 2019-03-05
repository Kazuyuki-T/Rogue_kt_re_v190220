/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kazuyuki.T
 */
public abstract class GameObject extends ImageLoader implements Cloneable{
    private String objName; // オブジェクトの名前
    private int objNum; // オブジェクトの管理番号

    private boolean active; // アクティブ，死 or アイテムゲットによりfalse

    // 座標
    private int posX;
    private int posY;

    public GameObject(){
        super();
        objName = null;
        objNum = -1;
        active = false;
        posX = -1;
        posY = -1;
    }
    
    public GameObject clone(){
        GameObject gameobject = null;
        try{
            gameobject = (GameObject)super.clone();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return gameobject;
    }
    
    public void activate(){
        active = true;
    }
    
    public void deactivate(){
        active = false;
    }
    
    public String getObjName(){ return objName; };
    public void setObjName(String objName){ this.objName = objName; };
    
    public int getObjNum(){ return objNum; };
    public void setObjNum(int objNum){ this.objNum = objNum; };
    
    public boolean getActive(){ return active; };
    public void setActive(boolean active){ this.active = active; };
    
    public int getPosX(){ return posX; };
    public void setPosX(int posX){ this.posX = posX; };
    
    public int getPosY(){ return posY; };
    public void setPosY(int posY){ this.posY = posY; };
}
