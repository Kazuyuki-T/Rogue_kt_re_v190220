import java.awt.Canvas;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;


public abstract class ImageLoader extends Canvas implements Cloneable {
    private BufferedImage img; // オブジェクトの持つ外見イメージ

    // 本来のimgサイズ
    private int sizeX;
    private int sizeY;
    
    public ImageLoader(){
        super();
        img = null;
        sizeX = 0;
        sizeY = 0;
    }
    
    public ImageLoader clone(){
        ImageLoader imageloader = null;
        try{
            imageloader = (ImageLoader)super.clone();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return imageloader;
    }
    
    public void loadImage(String name) {
        // イメージのロード
        try {
            FileInputStream input = new FileInputStream(name); // FileInputStream
            BufferedImage rv = ImageIO.read(input);
            input.close();
            img = rv;
            sizeX = img.getWidth();
            sizeY = img.getHeight();
        } catch (IOException e) {
            System.out.println("Err e=" + e);
            Logger.appendLog("Err e=" + e);
        }
    }

    public BufferedImage getImg() { return img; }
    public void setImg(BufferedImage img) { this.img = img; }
    
    public int getSizeX() { return sizeX; };
    public void setSizeX(int sizeX) { this.sizeX = sizeX; };
    
    public int getSizeY() { return sizeY; };
    public void setSizeY(int sizeY) { this.sizeY = sizeY; };
}