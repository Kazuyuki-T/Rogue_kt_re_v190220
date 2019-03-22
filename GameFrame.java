
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kazuyuki.T
 */

public class GameFrame extends ImageLoader implements Cloneable{
    private MainCanvas maincanvas;
    
    // フレームのサイズ
    private static final int FRAMESIZE_X = 1024;
    private static final int FRAMESIZE_Y = 900;
    
    // メインマップのサイズ
    private static final int MAINMAP_DRAWAREASIZE_X = 480;
    private static final int MAINMAP_DRAWAREASIZE_Y = 480;
    
    // メインマップの描画位置のオフセット
    private static final int MAINMAP_OFFSET_X = 20; // マップ描画の開始座標のオフセット，x座標
    private static final int MAINMAP_OFFSET_Y = 20; // マップ描画の開始座標のオフセット，y座標
    
    // サブマップのサイズ
    private static final int SUBMAP_DRAWAREASIZE_X = 480;
    private static final int SUBMAP_DRAWAREASIZE_Y = 480;
    
    // サブマップの描画位置のオフセット
    private static final int SUBMAP_OFFSET_X = 20;
    private static final int SUBMAP_OFFSET_Y = 20;
    
    public GameFrame() {
        super();
        
        //<editor-fold defaultstate="collapsed" desc="レイアウトの設定">
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        JPanel p = new JPanel();
        p.setLayout(layout);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="メニューバーの設定">
        JMenuBar menubar;
        JMenu menu1;
        JMenu menu2;
        JMenu menu3;
        JMenu menu4;
        JMenuItem menuitem1;
        JMenuItem menuitem2;
        JMenuItem menuitem3;

        menubar = new JMenuBar();
        menu1 = new JMenu("File"); // 仮決め，機能未実装
        menu2 = new JMenu("Menu");
        menu3 = new JMenu("Setting");
        menu4 = new JMenu("Option");
        menubar.add(menu1);
        menubar.add(menu2);
        menubar.add(menu3);
        menubar.add(menu4);
        menuitem1 = new JMenuItem("New"); // 仮決め
        menuitem2 = new JMenuItem("Open");
        menuitem3 = new JMenuItem("Close");
        menu1.add(menuitem1);
        menu1.add(menuitem2);
        menu1.add(menuitem3);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="テキストエリア（ログ用）の設定">
        JScrollPane scrollpane;
        Logger.logText = new JTextArea();
        Logger.logText.append("*-- log --*\n");
        scrollpane = new JScrollPane();
        scrollpane.setPreferredSize(new Dimension(250, 800));
        scrollpane.setMinimumSize(new Dimension(250, 800));
        Logger.logText.setEditable(false);
        Logger.logText.setLineWrap(true); // 折り返しアリ

        Logger.view = scrollpane.getViewport();
        Logger.view.setView(Logger.logText);

        // 初期値
        Logger.view.setViewPosition(new Point(0, 0));

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.weightx = 1.0d;
        gbc.weighty = 1.0d;
        gbc.fill = GridBagConstraints.VERTICAL;
        layout.setConstraints(scrollpane, gbc);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="キャンバス全般の設定">
        // キャンパスの作成(周囲視野)
        maincanvas = new MainCanvas(); // キャンバス
        //int frameSizeX = 1024;
        //int frameSizeY = 900;

        // フォルダ・ログ用の名前（日時）作成
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String folderName = simpleDateFormat.format(new Date(System.currentTimeMillis()));
        // フォルダ作成
        File file = new File(folderName);
        if (file.mkdir() == true) {
            //System.out.println("フォルダの作成に成功しました");
        } else {
            System.out.println("フォルダの作成に失敗しました");
        }

        //maincanvas.init(frameSizeX - 250, frameSizeY);
        maincanvas.setFrameSize(FRAMESIZE_X - 250, FRAMESIZE_Y);

        // 以下，抜くと描画が不可？サイズの変化が原因か
        maincanvas.setPreferredSize(new Dimension(FRAMESIZE_X - 250, FRAMESIZE_Y)); // 適切なサイズの設定
        maincanvas.setMinimumSize(new Dimension(FRAMESIZE_X - 250, FRAMESIZE_Y)); // 最小サイズの設定
        maincanvas.setMaximumSize(new Dimension(FRAMESIZE_X - 250, FRAMESIZE_Y)); // 最大サイズの設定
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.weightx = 1.0d;
        gbc.weighty = 1.0d;
        gbc.fill = GridBagConstraints.BOTH;
        layout.setConstraints(maincanvas, gbc);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Jフレームの作成">
        JFrame frame = new JFrame();

        frame.setSize(FRAMESIZE_X, FRAMESIZE_Y); // ウィンドウのサイズ，実際のサイズはもう少し小さい
        frame.setResizable(false); // サイズ変更不可
        frame.setTitle("RogeLike(" + folderName + ")"); // タイトル
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // クローズ

        // パネルへの追加
        p.add(scrollpane); // ログ
        p.add(maincanvas); // メイン
        frame.getContentPane().add(p, BorderLayout.CENTER);
        frame.setJMenuBar(menubar); // フレームにメニューバーを追加

        frame.setVisible(true); // ウィンドウの表示
        //</editor-fold>

        maincanvas.setOffScreenBuf(); // オフスクリーンバッファの作成，1度行う，setVisible(true)の後である必要あり
        maincanvas.init(); // 描画用ゲームデータの初期化
        
        loadImage("src/mat/mapchip.png"); // マップチップの読み込み
        
        // ログの管理どうすんねーん
        Logger.setLoggerLevel(0);
    }
    
    public void init(){
        maincanvas.init(); // 描画用ゲームデータの初期化
        // ログの初期化
    }
    
    public GameFrame Clone(){
        GameFrame gameframe = new GameFrame();
        try{
            gameframe = (GameFrame)super.clone();
            gameframe.maincanvas = this.maincanvas.clone();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return gameframe;
    }
    
    public void setState(State state){
        maincanvas.setState(state);
    }
    
    public void run(){
        // スレッド実行
        new Thread(maincanvas).start();
    }
    
    
    
    
    
    // インナークラス
    // 外側へアクセスできる？，setstate消して直接アクセスにする？フレームサイズとかも
    // GameFrame.this.-　でアクセスできるのでは？？
    private class MainCanvas extends Canvas implements Cloneable, Runnable {
        private Image imgBuf;
        private Graphics gBuf;
        private int fsizeX;
        private int fsizeY;
        private State state;
        private int test;
        
        public MainCanvas(){
            imgBuf = null;
            gBuf = null;
            fsizeX = 0;
            fsizeY = 0;
            state = null;
            test = 0;
        }
        
        public void setOffScreenBuf(){
            //オフスクリーンバッファ作成
            imgBuf = createImage(fsizeX, fsizeY);
            gBuf = imgBuf.getGraphics();
        }
        
        public void init(){
            // 情報の初期化
            state = null;
        }
        
        public void setFrameSize(int fsizeX, int fsizeY){
            this.fsizeX = fsizeX;
            this.fsizeY = fsizeY;
        }
        
        public MainCanvas clone(){
            MainCanvas maincanvas = new MainCanvas();
            try{
                maincanvas = (MainCanvas)super.clone();
            } catch(Exception e) {
                e.printStackTrace();
            }
            return maincanvas;
        }
        
        public void setState(State state){
            // 描画するstateの更新
            this.state = state;
        }
        
        public void setGBuf(){
            gBuf.setColor(Color.black);
            gBuf.fillRect(0, 0, fsizeX, fsizeY);
            
            // メインマップの描画順
            // 1:マップ（通行可不可）+縦横のグリッド線
            // 2:オブジェクト（階段等）
            // 3:アイテム
            // 4:ユニット（プレイヤ，敵）
            
            // stateから描画領域の指定
            // プレイヤを中心に，視界の範囲分を確認
            int playerPos_X = state.getPlayer().getPosX();
            int playerPos_Y = state.getPlayer().getPosY();
            int playerFoV_X = state.getFieldofViewX();
            int playerFoV_Y = state.getFieldofViewY();
            System.out.println("player:(" + playerPos_X + "," + playerPos_Y + ")");
            System.out.println("playerFoV:(" + playerFoV_X + "," + playerFoV_Y + ")");
            System.out.println("mapSize:(" + state.getMapSizeX() + "," + state.getMapSizeY() + ")");
            
            int gridSizeX = MAINMAP_DRAWAREASIZE_X / (playerFoV_X * 2 + 1); // 描画する1グリッドのサイズ，x, height
            int gridSizeY = MAINMAP_DRAWAREASIZE_Y / (playerFoV_Y * 2 + 1); // 描画する1グリッドのサイズ，y, width
            
            for(int gy = 0; gy < state.getMapSizeY(); gy++){
                for(int gx= 0; gx < state.getMapSizeX(); gx++){
                    System.out.print(state.getFlrInformation(state.getFlr()).getMap(gx, gy));
                }
                System.out.println();
            }
            
            // マップの描画
            for(int gy = playerPos_Y - playerFoV_Y, counterY = 0; gy <= playerPos_Y + playerFoV_Y; gy++, counterY++){
                for(int gx = playerPos_X - playerFoV_X, counterX = 0; gx <= playerPos_X + playerFoV_X; gx++, counterX++){
                    if(gy < 0 || state.getMapSizeY() <= gy || gx < 0 || state.getMapSizeX() <= gx){
                        // 0未満もしくはマップの最大値以上の場合，黒表示
                        //System.out.print("-1");
                    }
                    else{
                        int pickupGrid = state.getFlrInformation(state.getFlr()).getMap(gx, gy); // その他の場合，map[][]に従う
                        int drawPointX = counterX * gridSizeX;
                        int drawPointY = counterY * gridSizeY; 
                        if(pickupGrid == state.getFlrInformation(state.getFlr()).MAP_ROOM || pickupGrid == state.getFlrInformation(state.getFlr()).MAP_GATE || pickupGrid == state.getFlrInformation(state.getFlr()).MAP_PATH){
                            // 通行可能
                            //System.out.print("1 ");
                            // (img, 描画の左上，描画の右下，画像の左上，画像の右下，this)
                            gBuf.drawImage(getImg(), drawPointX + MAINMAP_OFFSET_X, drawPointY + MAINMAP_OFFSET_Y, drawPointX + gridSizeX + MAINMAP_OFFSET_X, drawPointY + gridSizeY + MAINMAP_OFFSET_Y, 0, 0, 32, 32, this);
                        }
                        else if(pickupGrid == state.getFlrInformation(state.getFlr()).MAP_WALL){
                            // 通行不可能
                            //System.out.print("0 ");
                            gBuf.drawImage(getImg(), drawPointX + MAINMAP_OFFSET_X, drawPointY + MAINMAP_OFFSET_Y, drawPointX + gridSizeX + MAINMAP_OFFSET_X, drawPointY + gridSizeY + MAINMAP_OFFSET_Y, 64, 0, 32 + 64, 32, this);
                        }
                        //System.out.println("drawPoint:(" + drawPointX + "," + drawPointY + ")," + pickupGrid);
                    }
                }
                //System.out.println();
            }
            
            // 横線の描画
            for(int counterY = 1; counterY <= playerFoV_Y * 2; counterY++){
                gBuf.setColor(Color.black);
                gBuf.fillRect(MAINMAP_OFFSET_X, counterY * gridSizeY - 1 + MAINMAP_OFFSET_Y, (playerFoV_X * 2 + 1) * gridSizeX, 2); // fillRect(x, y, width, height), x~x+width-1,y~y+height-1
            }
            
            // 縦線の描画
            for(int counterX = 1; counterX <= playerFoV_X * 2; counterX++){
                gBuf.setColor(Color.black);
                gBuf.fillRect(counterX * gridSizeX - 1 + MAINMAP_OFFSET_X, MAINMAP_OFFSET_Y, 2, (playerFoV_Y * 2 + 1) * gridSizeY); // fillRect(x, y, width, height), x~x+width-1,y~y+height-1
            }
            
            // オブジェクトの描画
            // gx,gy:map[][]の各インデックス, counterX,Y:視界内の左上からのグリッドカウント数
            for(int gy = playerPos_Y - playerFoV_Y, counterY = 0; gy <= playerPos_Y + playerFoV_Y; gy++, counterY++){
                for(int gx = playerPos_X - playerFoV_X, counterX = 0; gx <= playerPos_X + playerFoV_X; gx++, counterX++){
                    if(gy < 0 || state.getMapSizeY() <= gy || gx < 0 || state.getMapSizeX() <= gx){
                        // 0未満もしくはマップの最大値以上の場合，黒表示
                    }
                    else{
                        int pickupGrid = state.getFlrInformation(state.getFlr()).getObjMap(gx, gy); // その他の場合，map[][]に従う
                        int drawPointX = counterX * gridSizeX;
                        int drawPointY = counterY * gridSizeY; 
                        if(pickupGrid == 0){
                            // (img, 描画の左上，描画の右下，画像の左上，画像の右下，this)
                            //gBuf.drawImage(getImg(), drawPointX + MAINMAP_OFFSET_X, drawPointY + MAINMAP_OFFSET_Y, drawPointX + gridSizeX + MAINMAP_OFFSET_X, drawPointY + gridSizeY + MAINMAP_OFFSET_Y, 0, 0, 32, 32, this);
                        }
                        else if(pickupGrid == 1){
                            //gBuf.drawImage(getImg(), drawPointX + MAINMAP_OFFSET_X, drawPointY + MAINMAP_OFFSET_Y, drawPointX + gridSizeX + MAINMAP_OFFSET_X, drawPointY + gridSizeY + MAINMAP_OFFSET_Y, 64, 0, 32 + 64, 32, this);
                        }
                    }
                }
            }
            
            // アイテムの描画
            
            // ユニットの描画
            for(int gy = playerPos_Y - playerFoV_Y, counterY = 0; gy <= playerPos_Y + playerFoV_Y; gy++, counterY++){
                for(int gx = playerPos_X - playerFoV_X, counterX = 0; gx <= playerPos_X + playerFoV_X; gx++, counterX++){
                    if(gy < 0 || state.getMapSizeY() <= gy || gx < 0 || state.getMapSizeX() <= gx){
                        // 0未満もしくはマップの最大値以上の場合，黒表示
                        System.out.println("under 0 || over top");
                    }
                    else{
                        int pickupGrid = state.getFlrInformation(state.getFlr()).getUnitMap(gx, gy); // その他の場合，map[][]に従う
                        int drawPointX = counterX * gridSizeX;
                        int drawPointY = counterY * gridSizeY; 
                        if(pickupGrid == state.getFlrInformation(state.getFlr()).UNITMAP_PALYER){
                            // (img, 描画の左上，描画の右下，画像の左上，画像の右下，this)
                            gBuf.drawImage(state.getPlayer().getImg(), drawPointX + MAINMAP_OFFSET_X, drawPointY + MAINMAP_OFFSET_Y, drawPointX + gridSizeX + MAINMAP_OFFSET_X, drawPointY + gridSizeY + MAINMAP_OFFSET_Y, 0, 0, 32, 32, this);
                            System.out.println("player draw:" + pickupGrid);
                        }
                        else if(pickupGrid >= state.getFlrInformation(state.getFlr()).UNITMAP_ENEMY){
                            gBuf.drawImage(state.getEnemy(pickupGrid - state.getFlrInformation(state.getFlr()).UNITMAP_ENEMY).getImg(), drawPointX + MAINMAP_OFFSET_X, drawPointY + MAINMAP_OFFSET_Y, drawPointX + gridSizeX + MAINMAP_OFFSET_X, drawPointY + gridSizeY + MAINMAP_OFFSET_Y, 0, 0, 32, 32, this);
                            System.out.println("enemy draw:" + pickupGrid);
                        }
                    }
                }
            }
            
            
            // サブマップの描画
            
            // インベントリの描画
            
        }
        
        public void run(){
            System.out.println("start_thread");

            // バッファのクリア
            gBuf.setColor(Color.black);
            gBuf.fillRect(0, 0, fsizeX, fsizeY);
            
            while(true){
                try{
                    Thread.sleep(100); // 100msecスリープ
                }catch(InterruptedException e){
                    System.out.println(e);
                }
                
                setGBuf(); // 現在のstateを基に，バッファへ描画しておく
                
                // repaint()メソッドは実装の必要なし
                // update() -> paint() の順に呼び出す
                // 引数指定はできないので，グローバル変数でpaint()の内容を変更できるように
                repaint();
            }
        }
        
        // オーバーライド，クリア防止のため
        public void update(Graphics g) { 
            paint(g);
        }
        
        // 描画
        public void paint(Graphics g) {
            // ちらつき防止 -> オフスクリーンバッファ使用
            // オフスクリーンバッファの内容を自分にコピー
            g.drawImage(imgBuf, 0, 0, this);
        }
    }
}
