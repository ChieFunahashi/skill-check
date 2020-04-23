package q003;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Q003 集計と並べ替え
 * <p>
 * 以下のデータファイルを読み込んで、出現する単語ごとに数をカウントし、アルファベット辞書順に並び変えて出力してください。
 * resources/q003/data.txt
 * 単語の条件は以下となります
 * - "I"以外は全て小文字で扱う（"My"と"my"は同じく"my"として扱う）
 * - 単数形と複数形のように少しでも文字列が異れば別単語として扱う（"dream"と"dreams"は別単語）
 * - アポストロフィーやハイフン付の単語は1単語として扱う（"isn't"や"dead-end"）
 * <p>
 * 出力形式:単語=数
 * <p>
 * [出力イメージ]
 * （省略）
 * highest=1
 * I=3
 * if=2
 * ignorance=1
 * （省略）
 * <p>
 * 参考
 * http://eikaiwa.dmm.com/blog/4690/
 */
public class Q003 {
    /**
     * データファイルを開く
     * resources/q003/data.txt
     */
    private static InputStream openDataFile() {
        return Q003.class.getResourceAsStream("data.txt");
    }

    private static final String SEPARATOR = "(\\s|\\.|,|;)";

    public static void main(String[] args) {

        // 集計
        Map<String, Integer> map = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(openDataFile()))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] words = line.split(SEPARATOR);
                for (String word : words) {
                    if (!word.isEmpty()) {
                        if (!word.equals("I")) {
                            word = word.toLowerCase();

                        }

                        if (map.containsKey(word)) {
                            int count = map.get(word) + 1;
                            map.put(word, count);
                        } else {
                            map.put(word, 1);
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("ファイルが見つかりませんでした。");
        } catch (IOException e) {
            System.out.println("読み取りに失敗しました。");
        }

        // アルファベット順に並べ替え、つづりの長さ最大値取得
        List<String> list = new ArrayList<>();
        int maxLengthOfSpelling = 0;
        for (String key : map.keySet()) {
            if (!key.equals("-")) {
                list.add(key);
            }
            if (maxLengthOfSpelling < key.length()) {
                maxLengthOfSpelling = key.length();
            }
        }

        Collections.sort(list);

        // 出力
        String format = "%-" + maxLengthOfSpelling + "s= %1d";
        for (String word : list) {
            int count = map.get(word);
            System.out.printf(format, word, count);
            System.out.println();
        }
    }
}
// 完成までの時間: 45分