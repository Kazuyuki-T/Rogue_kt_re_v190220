
// ゲームのmainとなるクラス
// 

public class Game {
    public static void main(String args[]) {
        int dLevel = 1; // フレーム（出力）の有無の決定，0:フレーム出力なし，その他:出力あり
        Game game = new Game();
        game.newGame(dLevel);
    }

    public Game() {
        
    }
    
    public void newGame(int drawLv){
        if (drawLv != 0) {
            // フレーム出力あり，入力受付あり
            int gameMode = 1; // 0:人間，1:AI，2:実験用高速周回，3:AI１行動一時停止確認用
            int oplv = (gameMode == 2) ? 0 : 10; // 0:表示なし，10:表示あり
            Logger.setLoggerLevel(oplv);

            // ゲームマネージャの作成
            Manager mg = new Manager();
            mg.run_gui();
        }
        else {
            // ゲームマネージャの作成
            Manager mg = new Manager();
            mg.run_nogui();
        }
    }
}
