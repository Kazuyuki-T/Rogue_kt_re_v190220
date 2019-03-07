/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kazuyuki.T
 */

// 
// ゲームの次状態の取得，


import java.util.Random;

public class Rule implements Cloneable{
    private Random random;
    
    // パラメータ，開始したら変更しない
    private final int MAPSIZE_X = 50; // マップの横グリッド数
    private final int MAPSIZE_Y = 30; // マップの縦グリッド数 
    private final int MAX_FLR_NUM = 4; // フロア数
    private final int MAX_ENEMY_NUM_PER1FLR = 4; // 1フロア当たりの最大敵数
    
    public Rule(){
        random = new Random();
    }
    
    public Rule(int seed){
        random = new Random(seed);
    }
    
    public void init(){
        
    }
    
    public Rule Clone(){
        Rule rule = null;
        try{
            rule = (Rule)super.clone();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return rule;
    }
    
    public State getInitState(){
        State state = new State();
        
        // 初期化処理
        state.setTurn(0);
        state.setFlr(0);
        
        // 全フロアの構造を決定
        for(int fn = 0; fn < MAX_FLR_NUM; fn++){
            FlrInformation newflr = new FlrInformation(MAPSIZE_X, MAPSIZE_Y);
            newflr.createFlr(fn);
            state.addFlrInformation(newflr);
        }
        
        // プレイヤ・敵の初期化＆配置決定
        state.setPlayer(new Player());
        for(int en = 0; en < MAX_ENEMY_NUM_PER1FLR; en++){
            Enemy newenemy = new Enemy();
            // activate
            state.addEnemy(newenemy);
        }
        
        return state;
    }
    
    public State getNextState(State state, int act){
        State nextstate = null;
        
        // ターン経過処理
        
        // プレイヤの行動actの反映
        
        // 敵の行動
        
        return nextstate;
    }
}
