/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kazuyuki.T
 */
public class FlrInformation implements Cloneable {
    // 1階層分の情報を持つ
    // ダンジョンの形状，敵アイテム階段配置など
    
    private int flrNumber; // 何階層目か
    private int[][] map; // マップのみ，ユニット・オブジェクトなし
    private int[][] itemMap; // アイテムのみ
    private int[][] objMap; // オブジェクトのみ
    private int[][] unitMap; // ユニットのみ
    
    
    public FlrInformation(int mapsizeX, int mapsizeY){
        map = new int[mapsizeY][mapsizeX];
        itemMap = new int[mapsizeY][mapsizeX];
        objMap = new int[mapsizeY][mapsizeX];
        unitMap = new int[mapsizeY][mapsizeX];
    }
    
    public void init(){
        // 
    }
    
    // ディープコピー用
    public FlrInformation Clone(){ 
        FlrInformation flrinformation = null;
        try{
            flrinformation = (FlrInformation)super.clone();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return flrinformation;
    }

    public void createFlr(int flrNumber){
        this.flrNumber = flrNumber; // 階層の設定
        
        // パターンからmappukouzouno 決定
        
        // 
        
    }


    
    public int getFlrNumber(){ return flrNumber; }
    public void setFlrNumber(int flrNumber){ this.flrNumber = flrNumber; }
}
