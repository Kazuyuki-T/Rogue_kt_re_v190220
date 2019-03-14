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


import java.awt.Point;
import java.util.Random;

public class Rule implements Cloneable{
    private Random random;
    
    // パラメータ，開始したら変更しない
    private final int MAPSIZE_X = 50; // マップの横グリッド数
    private final int MAPSIZE_Y = 30; // マップの縦グリッド数 
    private final int MAX_FLR_NUM = 4; // フロア数
    private final int MAX_ENEMY_NUM_PER1FLR = 4; // 1フロア当たりの最大敵数
    private final long MAP_SEED = 0; // マップ生成のシード値
    private final int FLR_TYPE = 23; // フロアタイプ（4形状×各通路カット）
    private final int MAX_ITEM_NUM_PER1FLR = 4; // 1フロア当たりの最大アイテム数
    
    
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
        
        // プレイヤ・敵の初期化
        state.setPlayer(new Player());
        for(int en = 0; en < MAX_ENEMY_NUM_PER1FLR; en++){
            Enemy newenemy = new Enemy();
            state.addEnemy(newenemy);
        }
        
        // 全フロアの構造を決定，配置の決定
        Random mapRand = new Random(MAP_SEED); // マップ作成用
        for(int fn = 0; fn < MAX_FLR_NUM; fn++){
            FlrInformation newflr = new FlrInformation(MAPSIZE_X, MAPSIZE_Y);
            newflr.createFlr(fn, mapRand.nextInt(FLR_TYPE)); // 階層の作成，（階層数，フロアタイプ）
            
            int settableGridNumber = newflr.getSettableGridNumber(); // 新規フロアの設置可能数の獲得
            
            // 敵の配置の決定
            for(int en = 0; en < MAX_ENEMY_NUM_PER1FLR; en++, settableGridNumber--){
                Point enemypos = newflr.setEnemyPos(random.nextInt(settableGridNumber), en); // 配置の決定
                state.getEnemy(en).activate(enemypos.x, enemypos.y, 100, 100, 10, 10); // 初期フロアのenemyのアクティブ化
            }
            
            // アイテムの配置の決定
            int itemnum = MAX_ITEM_NUM_PER1FLR; // 適当に減少させる
            for(int in = 0; in < itemnum; in++, settableGridNumber--){
                // 配置するアイテムタイプの決定
                //Point ip = newflr.setItemPos(random.nextInt(settableGridNumber)); // 配置の決定
                
            }
            
            // objの配置の決定
            Point stairpos = newflr.setObjPos(random.nextInt(settableGridNumber), 0); // 配置の決定
            
            state.addFlrInformation(newflr);
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
