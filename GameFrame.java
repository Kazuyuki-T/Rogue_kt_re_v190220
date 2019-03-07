
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
import java.util.Random;
import java.util.ArrayList;
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

public class GameFrame implements Cloneable{
    private MainCanvas maincanvas;
    
    public GameFrame() {
        
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
        menu1 = new JMenu("File");
        menu2 = new JMenu("Menu");
        menu3 = new JMenu("Setting");
        menu4 = new JMenu("Option");
        menubar.add(menu1);
        menubar.add(menu2);
        menubar.add(menu3);
        menubar.add(menu4);
        menuitem1 = new JMenuItem("New");
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
        int frameSizeX = 1024;
        int frameSizeY = 900;

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
        maincanvas.setFrameSize(frameSizeX - 250, frameSizeY);

        // 以下，抜くと描画が不可？サイズの変化が原因か
        maincanvas.setPreferredSize(new Dimension(frameSizeX - 250, frameSizeY)); // 適切なサイズの設定
        maincanvas.setMinimumSize(new Dimension(frameSizeX - 250, frameSizeY)); // 最小サイズの設定
        maincanvas.setMaximumSize(new Dimension(frameSizeX - 250, frameSizeY)); // 最大サイズの設定
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

        frame.setSize(frameSizeX, frameSizeY); // ウィンドウのサイズ，実際のサイズはもう少し小さい
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
            this.state = state;
            
            test++;
            System.out.println(test);
            if(test % 2==0){
                gBuf.setColor(Color.white);
                gBuf.fillRect(0, 0, fsizeX, fsizeY);
            }
            else{
                gBuf.setColor(Color.red);
                gBuf.fillRect(0, 0, fsizeX, fsizeY);
            }
            
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
