package q007;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * q007 最短経路探索
 * <p>
 * 壁を 'X' 通路を ' ' 開始を 'S' ゴールを 'E' で表現された迷路で、最短経路を通った場合に
 * 何歩でゴールまでたどり着くかを出力するプログラムを実装してください。
 * もし、ゴールまで辿り着くルートが無かった場合は -1 を出力してください。
 * なお、1歩は上下左右のいずれかにしか動くことはできません（斜めはNG）。
 * <p>
 * 迷路データは MazeInputStream から取得してください。
 * 迷路の横幅と高さは毎回異なりますが、必ず長方形（あるいは正方形）となっており、外壁は全て'X'で埋まっています。
 * 空行が迷路データの終了です。
 * <p>
 * <p>
 * [迷路の例]
 * XXXXXXXXX
 * XSX    EX
 * X XXX X X
 * X   X X X
 * X X XXX X
 * X X     X
 * XXXXXXXXX
 * <p>
 * [答え]
 * 14
 */
public class Q007 {
    public static void main(String args[]) {

        MazeInputStream mazeInputStream = new MazeInputStream();
        List<String> list = new ArrayList<>();

        // 迷路データ読み込み
        try (BufferedReader br = new BufferedReader(new InputStreamReader(mazeInputStream));) {
            String line;

            while ((line = br.readLine()) != null) {
                if (!(line.isEmpty() || line.startsWith(" "))) {
                    list.add(line);
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 最短経路歩数


    }
}
// 完成までの時間: 1時間 30分(途中)