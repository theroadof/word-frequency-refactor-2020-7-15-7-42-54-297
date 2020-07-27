import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class WordFrequencyGame {

    private static final String SPLIT_REGEX = "\\s+";
    private static final String LINE_BREAK = "\n";
    private static final String ERROR_MESSAGE = "Calculate Error";
    private static final String SPACE = " ";

    public String getResult(String sentence) {


        if (sentence.split(SPLIT_REGEX).length==1) {
            return sentence + " 1";
        } else {

            try {

                String[] words = sentence.split(SPLIT_REGEX);

                List<WordInfo> wordInfos = new ArrayList<>();
                for (String word : words) {
                    WordInfo wordInfo = new WordInfo(word, 1);
                    wordInfos.add(wordInfo);
                }

                Map<String, List<WordInfo>> map =getListMap(wordInfos);

                List<WordInfo> list = new ArrayList<>();
                for (Map.Entry<String, List<WordInfo>> entry : map.entrySet()) {
                    WordInfo wordInfo = new WordInfo(entry.getKey(), entry.getValue().size());
                    list.add(wordInfo);
                }
                wordInfos = list;

                wordInfos.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());

                StringJoiner joiner = new StringJoiner(LINE_BREAK);
                for (WordInfo w : wordInfos) {
                    String s = w.getValue() + SPACE +w.getWordCount();
                    joiner.add(s);
                }
                return joiner.toString();
            } catch (Exception e) {
                return ERROR_MESSAGE;
            }
        }
    }

    private Map<String, List<WordInfo>> getListMap(List<WordInfo> wordInfoList) {
        Map<String, List<WordInfo>> map = new HashMap<>();
        for (WordInfo wordInfo : wordInfoList){
            if (!map.containsKey(wordInfo.getValue())) {
                ArrayList arr = new ArrayList<>();
                arr.add(wordInfo);
                map.put(wordInfo.getValue(), arr);
            }
            else {
                map.get(wordInfo.getValue()).add(wordInfo);
            }
        }
        return map;
    }
}
