
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
    private ArrayList<FlrInformation> flr_List; // フロア情報はリストとして所持
    
    public State(){
        enemy_List = new ArrayList<Enemy>(); // リストの宣言
        flr_List = new ArrayList<FlrInformation>();  // リストの宣言
    }
    
    public void init(){
        enemy_List.clear();
        flr_List.clear();
    }
        
    // ディープコピー用
    public State Clone(){ 
        State state = new State();
        try{
            // ラムダ式ループ
            this.flr_List.forEach(flrinfo -> {
                state.flr_List.add(flrinfo);
            });
        } catch(Exception e) {
            e.printStackTrace();
        }
        return state;
    }
    
    // 文字列 -> ゲーム情報
    // プレイヤが文字列から状態を判別するために使用
    // Ruleにここから得らる状態を投げて次状態の予測が可能
    public void string2state(String strinfo){
        
    }
    
    // ゲーム情報 -> 文字列
    public String state2string(){
        String state = new String();
        
        return state;
    }
    
    // プレイヤ用のゲーム情報の取得
    public String getStateForPlayer(){
        String state = new String();
        
        //
        
        return state;
    }
    
    public int getTurn(){ return turn; }
    public void setTurn(int turn){ this.turn = turn; }
    
    public int getFlr(){ return flr; }
    public void setFlr(int flr){ this.flr = flr; }
    
    public Player getPlayer(){ return player; }
    public void setPlayer(Player player){ this.player = player; }
    
    public ArrayList<Enemy> getEnemyList(){ return enemy_List; }
    public Enemy getEnemy(int enemy_index){ return enemy_List.get(enemy_index); }
    public void addEnemy(Enemy newenemy){ enemy_List.add(newenemy); }
    
    public ArrayList<FlrInformation> getFlrList(){ return flr_List; }
    public FlrInformation getFlrInformation(int flr_number){ return flr_List.get(flr_number); }
    public void addFlrInformation(FlrInformation newflr){ flr_List.add(newflr); }
}
