/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class Manager implements Cloneable {
    private Rule rule;
    private State state; 
    private GameFrame gameframe;
    private boolean gameframeInitFlag; // true:作成済み, false:未作成
    
    public Manager() {
        rule = new Rule();
        gameframeInitFlag = false; // ゲームフレーム未作成
    }
    
    public void initState(){
        state = rule.getInitState(); // ゲーム情報の初期化
    }
    
    // ディープコピー用
    public Manager Clone(){ 
        Manager manager = null;
        try{
            manager = (Manager)super.clone();
            manager.state = this.state.Clone();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return manager;
    }
    
    // ゲームのメインループ，guiあり
    public void run_gui_AI(){
        initState(); // ゲーム開始直前に初期化を挟む
        
        if(gameframeInitFlag == false){
            // フレームの作成，描画の開始
            gameframe = new GameFrame(); // フレームの構築
            gameframe.run(); // 描画開始（スレッドスタート），次回以降は不要
            gameframeInitFlag = true;
        }
        
        while(true){
            // プレイヤが確認できる範囲の情報
            String str_state = state.getStateForPlayer();

            // 情報をキャンバスに渡し，描画
            gameframe.setState(state);

            // ai:方向+行動->ターンを進める
            // 人:
            
            // ターンを進める

            
            // ゲーム終了ならば脱ループ
            //if(true) break;
            
            
            try {
                Thread.sleep(1000); // 100msecスリープ
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
        
        //return true; // 結果を返す
    }
    
    // ゲームのメインループ，guiあり
    public void run_gui_HUMAN(){
        initState(); // ゲーム開始直前に初期化を挟む
        
        if(gameframeInitFlag == false){
            // フレームの作成，描画の開始
            gameframe = new GameFrame(); // フレームの構築
            gameframe.run(); // 描画開始（スレッドスタート），次回以降は不要
            gameframeInitFlag = true;
        }
        
        KeyInput keyinput = new KeyInput(); // 入力に対応できるように
        
        while(true){
            // プレイヤが確認できる範囲の情報
            String str_state = state.getStateForPlayer();

            // 情報をキャンバスに渡し，描画
            gameframe.setState(state);

            // 人:
            rule.getNextState(state, keyinput)
            
            
            
            // ゲーム終了ならば脱ループ
            //if(true) break;
            
            
            try {
                Thread.sleep(1000); // 100msecスリープ
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
        
        //return true; // 結果を返す
    }
    
    // ゲームのメインループ，guiなし
    public boolean run_nogui(){
        initState(); // ゲーム開始直前に初期化を挟む
        
        while(true){
            // プレイヤが確認できる範囲の情報
            String str_state = state.getStateForPlayer();

            // ターンを進める
            
            
            // ゲーム終了ならば脱ループ
            if(true) break;
        }
        
        return true; // 結果を返す
    }
}
