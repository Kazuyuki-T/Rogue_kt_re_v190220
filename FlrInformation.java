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
    
    public FlrInformation(){
        
    }
    
    public void init(){
        // 
    }
        
    // ディープコピー用
    public FlrInformation Clone(){ 
        FlrInformation flrinformation = new FlrInformation();
        try{
            
        } catch(Exception e) {
            e.printStackTrace();
        }
        return flrinformation;
    }
}
