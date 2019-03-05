
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kazuyuki.T
 */
public class State implements Cloneable {
    private int turn;
    private int flr;
    private Player player;
    private ArrayList<Enemy> enemy_List; // 現フロアの敵情報
    private ArrayList<FlrInformation> flrinfo_List; // フロア情報はリストとして所持
            
    public State(){
        
        
        
        
    }
    
    public void init(){
        // 
        
        flrinfo_List.clear();
    }
        
    // ディープコピー用
    public State Clone(){ 
        State state = new State();
        try{
            // ラムダ式ループ
            this.flrinfo_List.forEach(flrinfo -> {
                state.flrinfo_List.add(flrinfo);
            });
        } catch(Exception e) {
            e.printStackTrace();
        }
        return state;
    }
    
    // 文字列 -> ゲーム情報
    public void string2info(String strinfo){
        
    }
    
    // ゲーム情報 -> 文字列
    public String info2string(){
        String strinfo = new String();
        
        return strinfo;
    }
    
    // プレイヤ用のゲーム情報の取得
    public String getPlayerInfo(){
        String strinfo = new String();
        
        //
        
        return strinfo;
    }
}
