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

public class Rule implements Cloneable{
    public Rule(){
        
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
    
    public State initState(){
        State state = null;
        
        return state;
    }
    
    public State getNextState(State state, int act){
        State nextstate = null;
        
        return nextstate;
    }
    
    
}
