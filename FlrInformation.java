
import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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
    private int roomNumber; // 部屋数
    private int pathNumber; // 通路数
    private int movableGridNumber; // 移動可能なマス数
    private int unmovableGridNumber; // 移動不可なマス数
    private int settableGridNumber; // 部屋内の合計マス数
    
    private int[][] map; // マップのみ，ユニット・オブジェクトなし，0:通行不可,1:通行可,2:部屋内通路手前
    private int[][] itemMap; // アイテムのみ，0:なし1:
    private int[][] objMap; // オブジェクトのみ，0:なし1:階段
    private int[][] unitMap; // ユニットのみ，0:なし1:プレイヤ2-:敵
    
    public final int MAP_WALL = 0; // 移動不可
    public final int MAP_ROOM = 1; // 移動可，部屋
    public final int MAP_GATE = 2; // 移動可，部屋の出入口　初期化時にオブジェクトの配置をしない
    public final int MAP_PATH = 3; // 移動可，通路

    public final int UNITMAP_EMPTY = 0;
    public final int UNITMAP_PALYER = 1;
    public final int UNITMAP_ENEMY = 2;
    
    public final int ITEMMAP_EMPTY = 0;
    public final int ITEMMAP_POTION = 1;
    public final int ITEMMAP_ARROW = 2;
    public final int ITEMMAP_STAFF = 3;
    public final int ITEMMAP_FOOD = 4;
    
    public final int OBJMAP_EMPTY = 1;
    public final int OBJMAP_STAIR = 1;
    
    public FlrInformation(int mapsizeX, int mapsizeY){
        map = new int[mapsizeY][mapsizeX];
        itemMap = new int[mapsizeY][mapsizeX];
        objMap = new int[mapsizeY][mapsizeX];
        unitMap = new int[mapsizeY][mapsizeX];
        init();
    }
    
    public void init(){
        // 初期化
        for(int y = 0; y < map.length; y++){
            for(int x = 0; x < map[y].length; x++){
                map[y][x] = 1;
                itemMap[y][x] = 0;
                objMap[y][x] = 0;
                unitMap[y][x] = 0;
            }
        }
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

    public void createFlr(int flrNumber, int flrType){
        this.flrNumber = flrNumber; // 階層の設定
        
        // マップ構造をtxtファイルから読み込み
        loadMap(new String("src/mat/flrtype" + flrType + ".txt")); // 引数：mapファイル名
    }

    public void loadMap(String failname) {
        try {
            File file = new File(failname);
            FileReader filereader = new FileReader(file);
            BufferedReader br = new BufferedReader(filereader);

            //一行ずつ読み込んで
            String str;
            int count;

            count = 0;
            while ((str = br.readLine()) != null) {
                // １行目はflrtypeなのでスキップ
                // ２行目は移動可能マスの数，移動不可能マスの数，初期配置可能マスの数
                if(count == 1){
                    int[] blocknum;
                    blocknum = parseInts(str.split(","));
                    movableGridNumber = blocknum[0];
                    unmovableGridNumber = blocknum[1];
                    settableGridNumber = blocknum[2];
                    break;
                }
                else{
                    count++;
                }
            }
            
            // 部屋数とそれらの座標の読み込み
            count = 0;
            while ((str = br.readLine()) != null) {
                if (count == 0) {
                    roomNumber = Integer.parseInt(str); // 部屋数の格納
                    count++;
                }
                else if(roomNumber == count){
                    break; // 部屋数分の座標格納が終了したら
                }
                else {
                    // 座標の格納
                    
                    // x,y,x,yの並びを一時的に格納
                    int[] tmpPoint = new int[4];
                    tmpPoint = parseInts(str.split(","));

                    // アレイリストに追加するために整理
                    //Background.RoomPoint newrp = new Background.RoomPoint();
                    //newrp.topLeft = new Point(tmpPoint[0], tmpPoint[1]);
                    //newrp.bottmRight = new Point(tmpPoint[2], tmpPoint[3]);
                    //rpList.add(newrp);
                    count++;
                }
            }

            // 通路数とそれらの座標の読み込み
            count = 0;
            while ((str = br.readLine()) != null) {
                if (count == 0) {
                    pathNumber = Integer.parseInt(str); // 部屋数の確認
                    count++;
                }
                else if(pathNumber == count){
                    break;
                }
                else {
                    // 座標の格納
                    int[] tmpPoint = new int[4];
                    tmpPoint = parseInts(str.split(","));

                    //Background.PassPoint newpp = new Background.PassPoint();
                    //newpp.topLeft = new Point(tmpPoint[0], tmpPoint[1]);
                    //newpp.bottmRight = new Point(tmpPoint[2], tmpPoint[3]);
                    //ppList.add(newpp);
                    count++;
                }
            }

            // マップの読み込み
            count = 0;
            while ((str = br.readLine()) != null) {
                // 一行の内容を','で分割してそれぞれを[count=ノード番号]の２次元目の配列の要素として格納
                map[count] = parseInts(str.split(","));
                count++;
            }

            br.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
    public Point setPlayerPos(int setNumber){
        int count = 0;
        int px = 0;
        int py = 0;
        
        System.out.println("setNumber:" + setNumber);
                        
        
        // 左上から見て，部屋内かつ出入口でないとき，カウント
        
        for(int y = 0; y < map.length; y++){
            for(int x = 0; x < map[y].length; x++){
                if(map[y][x] == MAP_ROOM && unitMap[y][x] == UNITMAP_EMPTY){
                    if(count == setNumber) {
                        System.out.println("x,y:" + x + "," + y);
                        System.out.println("count:" + count);
                        px = x;
                        py = y;
                        unitMap[y][x] = 1; // 配置可能座標の時，更新
                        return new Point(px, py);
                    }
                    else{
                        count++; // 配置可能な部分をカウント
                    }
                }
            }
        }
        
        System.out.println("error : set PALYER position failure");
        return new Point(-1, -1); // エラー処理
    }
    
    public Point setEnemyPos(int setNumber, int enemyControlNum){
        int count = 0;
        int ex = 0;
        int ey = 0;
        
        for(int y = 0; y < map.length; y++){
            for(int x = 0; x < map[y].length; x++){
                if(map[y][x] == MAP_ROOM && unitMap[y][x] == UNITMAP_EMPTY){
                    if(count == setNumber) {
                        ex = x;
                        ey = y;
                        unitMap[y][x] = enemyControlNum + UNITMAP_ENEMY; // 配置可能座標の時，更新，敵は2以降
                        return new Point(ex, ey);
                    }
                    else{
                        count++; // 配置可能な部分をカウント
                    }
                }
            }
        }
        
        System.out.println("error : set ENEMY position failure");
        return new Point(-1, -1);
    }
    
    public Point setItemPos(int setNumber, int itemtype){
        int count = 0;
        int ix = 0;
        int iy = 0;
        
        for(int y = 0; y < map.length; y++){
            for(int x = 0; x < map[y].length; x++){
                if(map[y][x] == MAP_ROOM && itemMap[y][x] == ITEMMAP_EMPTY){
                    if(count == setNumber) {
                        ix = x;
                        iy = y;
                        itemMap[y][x] = 1; // 配置可能座標の時，更新
                        return new Point(ix, iy);
                    }
                    else{
                        count++; // 配置可能な部分をカウント
                    }
                }
            }
        }
        
        System.out.println("error : set ITEM position failure");
        return new Point(-1, -1);
    }
    
    public Point setObjPos(int setNumber, int objControlNumber){
        int count = 0;
        int objx = 0;
        int objy = 0;
        
        for(int y = 0; y < map.length; y++){
            for(int x = 0; x < map[y].length; x++){
                if(map[y][x] == MAP_ROOM){
                    if(count == setNumber) {
                        objx = x;
                        objy = y;
                        itemMap[y][x] = 1; // 配置可能座標の時，更新
                    }
                    else{
                        count++; // 配置可能な部分をカウント
                    }
                }
            }
        }
        
        return new Point(objx, objy);
    }
    
    // s[] = intに変換したいストリングを収めた配列
    public int[] parseInts(String[] s) {
        int[] x = new int[s.length];
        for (int i = 0; i < s.length; i++) {
            x[i] = Integer.parseInt(s[i]);
        }
        return x;
    }
    
    public Point aaa(){
        return new Point(0, 0);
    }
    
    // ==============================
    // ゲッター，セッター
    // ==============================
    
    public int getFlrNumber(){ return flrNumber; }
    public void setFlrNumber(int flrNumber){ this.flrNumber = flrNumber; }
    
    public int getRoomNumber(){ return roomNumber; }
    public void setRoomNumber(int roomNumber){ this.roomNumber = roomNumber; }
    
    public int getPathNumber(){ return pathNumber; }
    public void setPathNumber(int pathNumber){ this.pathNumber = pathNumber; }
    
    public int getMovableGridNumber(){ return movableGridNumber; }
    public void setMovableGridNumber(int movableGridNumber){ this.movableGridNumber = movableGridNumber; }
    
    public int getUnmovableGridNumber(){ return unmovableGridNumber; }
    public void setUnmovableGridNumber(int unmovableGridNumber){ this.unmovableGridNumber = unmovableGridNumber; }
    
    public int getSettableGridNumber(){ return settableGridNumber; }
    public void setSettableGridNumber(int settableGridNumber){ this.settableGridNumber = settableGridNumber; }
    
    public int getMap(int x, int y){ return map[y][x]; }
    public int getItemMap(int x, int y){ return itemMap[y][x]; }
    public int getObjMap(int x, int y){ return objMap[y][x]; }
    public int getUnitMap(int x, int y){ return unitMap[y][x]; }
}
