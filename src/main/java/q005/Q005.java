package q005;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Q005 データクラスと様々な集計
 * <p>
 * 以下のファイルを読み込んで、WorkDataクラスのインスタンスを作成してください。
 * resources/q005/data.txt
 * (先頭行はタイトルなので読み取りをスキップする)
 * <p>
 * 読み込んだデータを以下で集計して出力してください。
 * (1) 役職別の合計作業時間
 * (2) Pコード別の合計作業時間
 * (3) 社員番号別の合計作業時間
 * 上記項目内での出力順は問いません。
 * <p>
 * 作業時間は "xx時間xx分" の形式にしてください。
 * また、WorkDataクラスは自由に修正してください。
 * <p>
 * [出力イメージ]
 * 部長: xx時間xx分
 * 課長: xx時間xx分
 * 一般: xx時間xx分
 * Z-7-31100: xx時間xx分
 * I-7-31100: xx時間xx分
 * T-7-30002: xx時間xx分
 * （省略）
 * 194033: xx時間xx分
 * 195052: xx時間xx分
 * 195066: xx時間xx分
 * （省略）
 */
public class Q005 {
    /**
     * データファイルを開く
     * resources/q003/data.txt
     */
    private static InputStream openDataFile() {
        return Q005.class.getResourceAsStream("data.txt");
    }

    public static void main(String[] args) {

        List<WorkData> workDataList = new ArrayList<WorkData>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(openDataFile()))) {
            String line;

            while ((line = br.readLine()) != null) {
                if (line.startsWith("社員番号")) {
                    continue;
                }

                String[] words = line.split("\\,", -1);

                workDataList.add(new WorkData(
                        words[0],
                        words[1],
                        words[2],
                        words[3],
                        Integer.parseInt(words[4])
                ));
            }
        } catch (FileNotFoundException e) {
            System.out.println("ファイルが見つかりませんでした。");
        } catch (IOException e) {
            System.out.println("読み取りに失敗しました。");
        }

        // (1) 役職別の合計作業時間
        Map<String, Integer> summaryByPosition = getSummaryByPosition(workDataList);
        // (2) Pコード別の合計作業時間
        Map<String, Integer> summaryByPCode = getSummaryByPCode(workDataList);
        // (3) 社員番号別の合計作業時間
        Map<String, Integer> summaryByNumber = getSummaryByNumber(workDataList);

        // 集計を出力する
        showResult(summaryByPosition);
        showResult(summaryByPCode);
        showResult(summaryByNumber);
    }

    /**
     * 役職別の合計作業時間を集計します。
     *
     * @param workDataList
     * @return
     */
    private static Map<String, Integer> getSummaryByPosition(List<WorkData> workDataList) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (WorkData workData : workDataList) {
            if (map.containsKey(workData.getPosition())) {
                map.put(workData.getPosition(),
                        map.get(workData.getPosition()) + workData.getWorkTime());
            } else {
                map.put(workData.getPosition(), workData.getWorkTime());
            }
        }
        return map;
    }

    /**
     * Pコード別の合計作業時間を集計します。
     *
     * @param workDataList
     * @return
     */
    private static Map<String, Integer> getSummaryByPCode(List<WorkData> workDataList) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (WorkData workData : workDataList) {
            if (map.containsKey(workData.getpCode())) {
                map.put(workData.getpCode(),
                        map.get(workData.getpCode()) + workData.getWorkTime());
            } else {
                map.put(workData.getpCode(), workData.getWorkTime());
            }
        }
        return map;
    }

    /**
     * 社員番号別の合計作業時間を集計します。
     *
     * @param workDataList
     * @return
     */
    private static Map<String, Integer> getSummaryByNumber(List<WorkData> workDataList) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (WorkData workData : workDataList) {
            if (map.containsKey(workData.getNumber())) {
                map.put(workData.getNumber(),
                        map.get(workData.getNumber()) + workData.getWorkTime());
            } else {
                map.put(workData.getNumber(), workData.getWorkTime());
            }
        }
        return map;
    }

    /**
     * 集計結果を出力します。
     *
     * @param tempMap
     * @return
     */
    private static void showResult(Map<String, Integer> tempMap) {
        Set<String> set = tempMap.keySet();
        for (String key : set) {
            System.out.println(key + ": " + convertTime(tempMap.get(key)));
        }
    }

    /**
     * 作業時間変換処理。
     *
     * @param time
     * @return
     */
    private static String convertTime(int time) {
        int hour = time / 60;
        int minute = time % 60;
        return String.format("%4d", hour) + "時間" + String.format("%02d", minute) + "分";
    }


}
// 完成までの時間: 3時間 15分